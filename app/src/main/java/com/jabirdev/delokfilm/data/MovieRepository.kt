package com.jabirdev.delokfilm.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.jabirdev.delokfilm.app.TMDBConstants
import com.jabirdev.delokfilm.data.source.local.LocalDataSource
import com.jabirdev.delokfilm.data.source.local.entity.*
import com.jabirdev.delokfilm.data.source.remote.ApiResponse
import com.jabirdev.delokfilm.data.source.remote.RemoteDataSource
import com.jabirdev.delokfilm.data.source.remote.response.CreditsDataModel
import com.jabirdev.delokfilm.data.source.remote.response.MovieDataModel
import com.jabirdev.delokfilm.data.source.remote.response.TvShowDataModel
import com.jabirdev.delokfilm.utils.AppExecutors
import com.jabirdev.delokfilm.vo.Resource

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    companion object {
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getPopularMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieDataModel.MovieResult>>(
                appExecutors
            ) {

            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieDataModel.MovieResult>>> =
                remoteDataSource.getPopularMovie()

            override fun saveCallResult(data: List<MovieDataModel.MovieResult>) {
                val movies = ArrayList<MovieEntity>()
                data.forEach {
                    val m = MovieEntity(
                        it.id, it.overview, it.popularity, it.posterPath, it.releaseDate,
                        it.title, it.voteAverage, it.voteCount
                    )
                    movies.add(m)
                }

                localDataSource.insertPopularMovies(movies)

            }

        }.asLiveData()
    }

    override fun getPopularTv(): LiveData<Resource<PagedList<TvEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvEntity>, List<TvShowDataModel.TVShowResult>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(6)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularTv(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowDataModel.TVShowResult>>> =
                remoteDataSource.getPopularTvShow()

            override fun saveCallResult(data: List<TvShowDataModel.TVShowResult>) {
                val tv = ArrayList<TvEntity>()
                data.forEach {
                    val t = TvEntity(
                        it.id, it.firstAirDate, it.name, it.overview, it.popularity,
                        it.posterPath, it.voteAverage, it.voteCount
                    )
                    tv.add(t)
                }
                localDataSource.insertPopularTv(tv)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(id: String): LiveData<Resource<MovieEntity>> {
        return object :
            NetworkBoundResource<MovieEntity, MovieDataModel.MovieResult>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getDetailMovie(id.toInt())

            override fun shouldFetch(data: MovieEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<MovieDataModel.MovieResult>> =
                remoteDataSource.getDetailMovie(id)

            override fun saveCallResult(data: MovieDataModel.MovieResult) {
                val movie = MovieEntity(
                    data.id, data.overview, data.popularity, data.posterPath, data.releaseDate,
                    data.title, data.voteAverage, data.voteCount
                )
                localDataSource.insertDetailMovie(movie)
            }
        }.asLiveData()
    }

    override fun getDetailTv(id: String): LiveData<Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, TvShowDataModel.TVShowResult>(appExecutors) {
            override fun loadFromDB(): LiveData<TvEntity> = localDataSource.getDetailTv(id.toInt())

            override fun shouldFetch(data: TvEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<TvShowDataModel.TVShowResult>> =
                remoteDataSource.getDetailTvShow(id)

            override fun saveCallResult(data: TvShowDataModel.TVShowResult) {
                val tv = TvEntity(
                    data.id, data.firstAirDate, data.name, data.overview, data.popularity,
                    data.posterPath, data.voteAverage, data.voteCount
                )
                localDataSource.insertDetailTv(tv)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTv(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTv(), config).build()
    }

    override fun getMovieWithCredit(id: String): LiveData<Resource<MovieWithCredit>> {
        return object :
            NetworkBoundResource<MovieWithCredit, List<CreditsDataModel.CastModel>>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieWithCredit> =
                localDataSource.getMovieWithCredit(id.toInt())

            override fun shouldFetch(data: MovieWithCredit?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<List<CreditsDataModel.CastModel>>> =
                remoteDataSource.getCredits(TMDBConstants.PATH_MOVIES, id)

            override fun saveCallResult(data: List<CreditsDataModel.CastModel>) {
                val credit = ArrayList<CreditsEntity>()
                data.forEach {
                    val c = CreditsEntity(
                        it.id, id.toInt(), it.name, it.profilePath, it.character
                    )
                    credit.add(c)
                }
                localDataSource.insertMovieCredit(credit)
            }
        }.asLiveData()
    }

    override fun getTvWithCredit(id: String): LiveData<Resource<TvWithCredit>> {
        return object :
            NetworkBoundResource<TvWithCredit, List<CreditsDataModel.CastModel>>(appExecutors) {
            override fun loadFromDB(): LiveData<TvWithCredit> =
                localDataSource.getTvWithCredit(id.toInt())

            override fun shouldFetch(data: TvWithCredit?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<List<CreditsDataModel.CastModel>>> =
                remoteDataSource.getCredits(TMDBConstants.PATH_TV_SHOW, id)

            override fun saveCallResult(data: List<CreditsDataModel.CastModel>) {
                val credit = ArrayList<CreditsTvEntity>()
                data.forEach {
                    val c = CreditsTvEntity(
                        it.id, id.toInt(), it.name, it.profilePath, it.character
                    )
                    credit.add(c)
                }
                localDataSource.insertTvCredit(credit)
            }
        }.asLiveData()
    }

    override fun getAllMovieCredit(id: String): LiveData<Resource<List<CreditsEntity>>> {
        return object :
            NetworkBoundResource<List<CreditsEntity>, List<CreditsDataModel.CastModel>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<CreditsEntity>> =
                localDataSource.getCreditByMovieId(id.toInt())

            override fun shouldFetch(data: List<CreditsEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CreditsDataModel.CastModel>>> =
                remoteDataSource.getCredits(TMDBConstants.PATH_MOVIES, id)

            override fun saveCallResult(data: List<CreditsDataModel.CastModel>) {
                val credit = ArrayList<CreditsEntity>()
                data.forEach {
                    val c = CreditsEntity(
                        it.id, id.toInt(), it.name, it.profilePath, it.character
                    )
                    credit.add(c)
                }
                localDataSource.insertMovieCredit(credit)
            }
        }.asLiveData()
    }

    override fun getAllTvCredit(id: String): LiveData<Resource<List<CreditsTvEntity>>> {
        return object :
            NetworkBoundResource<List<CreditsTvEntity>, List<CreditsDataModel.CastModel>>(
                appExecutors
            ) {
            override fun loadFromDB(): LiveData<List<CreditsTvEntity>> =
                localDataSource.getCreditByTvId(id.toInt())

            override fun shouldFetch(data: List<CreditsTvEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CreditsDataModel.CastModel>>> =
                remoteDataSource.getCredits(TMDBConstants.PATH_TV_SHOW, id)

            override fun saveCallResult(data: List<CreditsDataModel.CastModel>) {
                val credit = ArrayList<CreditsTvEntity>()
                data.forEach {
                    val c = CreditsTvEntity(
                        it.id, id.toInt(), it.name, it.profilePath, it.character
                    )
                    credit.add(c)
                }
                localDataSource.insertTvCredit(credit)
            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }

    override fun setFavoriteTv(tv: TvEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteTv(tv, state) }

}