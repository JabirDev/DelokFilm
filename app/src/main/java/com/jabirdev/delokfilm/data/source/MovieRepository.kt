package com.jabirdev.delokfilm.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jabirdev.delokfilm.data.*
import com.jabirdev.delokfilm.data.source.remote.RemoteDataSource
import com.jabirdev.delokfilm.models.*
import com.jabirdev.delokfilm.utils.JsonHelper
import okhttp3.ResponseBody

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    override fun getPopularMovie(): LiveData<List<MovieEntity>> {
        val popularMovie = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getPopularMovie(object : JsonHelper.ResultListener<MovieDataModel>{
            override fun onSuccess(successModel: MovieDataModel?) {
                val movieList = ArrayList<MovieEntity>()
                successModel?.let { result ->
                    result.results.forEach {
                        val movie = MovieEntity(
                            it.adult, it.backdropPath, it.genreIds, it.id, it.originalLanguage, it.originalTitle,
                            it.overview, it.popularity, it.posterPath, it.releaseDate, it.title, it.video,
                            it.voteAverage, it.voteCount
                        )
                        movieList.add(movie)
                    }
                }
                popularMovie.postValue(movieList)
            }

            override fun onError(error: ResponseBody?) {
                Log.e("Popular Movies", "Error: ${error.toString()}")
            }

            override fun onFailure(t: Throwable?) {
                Log.e("Popular Movies", "Failure: ", t)
            }
        })
        return popularMovie
    }

    override fun getPopularTv(): LiveData<List<TvEntity>> {
        val popularTv = MutableLiveData<List<TvEntity>>()
        remoteDataSource.getPopularTvShow(object : JsonHelper.ResultListener<TvShowDataModel>{
            override fun onSuccess(successModel: TvShowDataModel?) {
                val tvList = ArrayList<TvEntity>()
                successModel?.let { result ->
                    result.results.forEach {
                        val tv = TvEntity(
                            it.backdropPath, it.adult, it.genreIds, it.id, it.name, it.originCountry, it.originalLanguage,
                            it.originalName, it.overview, it.popularity, it.posterPath, it.voteAverage, it.voteCount
                        )
                        tvList.add(tv)
                    }
                }
                popularTv.postValue(tvList)
            }

            override fun onError(error: ResponseBody?) {
                Log.e("Popular TV", "Error: ${error.toString()}")
            }

            override fun onFailure(t: Throwable?) {
                Log.e("Popular TV", "Failure: ", t)
            }
        })
        return popularTv
    }

    override fun getDetailMovie(id: String): LiveData<DetailMovieEntity> {
        val detailMovie = MutableLiveData<DetailMovieEntity>()
        remoteDataSource.getDetailMovie(id, object : JsonHelper.ResultListener<DetailMovieModel>{
            override fun onSuccess(successModel: DetailMovieModel?) {
                successModel?.let {
                    val movie = DetailMovieEntity(
                        it.adult, it.backdropPath, it.belongsToCollection, it.budget, it.genres, it.homepage, it.id,
                        it.imdbId, it.originalLanguage, it.originalTitle, it.overview, it.popularity, it.posterPath,
                        it.productionCompanies, it.productionCountries, it.releaseDate, it.revenue, it.runtime, it.spokenLanguages,
                        it.status, it.tagline, it.title, it.video, it.voteAverage, it.voteCount
                    )
                    detailMovie.postValue(movie)
                }
            }

            override fun onError(error: ResponseBody?) {

            }

            override fun onFailure(t: Throwable?) {

            }
        })
        return detailMovie
    }

    override fun getDetailTv(id: String): LiveData<DetailTvEntity> {
        val detailTv = MutableLiveData<DetailTvEntity>()
        remoteDataSource.getDetailTvShow(id, object : JsonHelper.ResultListener<DetailTvShowModel>{
            override fun onSuccess(successModel: DetailTvShowModel?) {
                successModel?.let {
                    val tv = DetailTvEntity(
                        it.backdropPath, it.episodeRuntime, it.firstAirDate, it.genres, it.homepage, it.id,
                        it.inProduction, it.languages, it.lastAirDate, it.lastEpisodeToAir, it.name, it.nextEpisodeToAir, it.networks,
                        it.numberOfEpisodes, it.numberOfSeasons, it.originCountry, it.originalLanguage, it.originalName, it.overview,
                        it.popularity, it.posterPath, it.productionCompanies, it.productionCountries, it.seasons, it.spokenLanguage, it.status,
                        it.tagline, it.type, it.voteAverage, it.voteCount
                    )
                    detailTv.postValue(tv)
                }
            }

            override fun onError(error: ResponseBody?) {

            }

            override fun onFailure(t: Throwable?) {

            }
        })
        return detailTv
    }

    override fun getCredits(path: String, id: String): LiveData<List<CreditsEntity>> {
        val credits = MutableLiveData<List<CreditsEntity>>()
        remoteDataSource.getCredits(path, id, object : JsonHelper.ResultListener<CreditsDataModel>{
            override fun onSuccess(successModel: CreditsDataModel?) {
                val creditList = ArrayList<CreditsEntity>()
                successModel?.let {
                    it.cast.forEach { cast ->
                        val cr = CreditsEntity(
                            cast.adult, cast.gender, cast.id, cast.knownForDepartment, cast.name, cast.originalName, cast.popularity,
                            cast.profilePath, cast.castId, cast.character, cast.creditId, cast.order
                        )
                        creditList.add(cr)
                    }
                }
                credits.postValue(creditList)
            }

            override fun onError(error: ResponseBody?) {
                Log.e("Credits", "Error: ${error.toString()}")
            }

            override fun onFailure(t: Throwable?) {
                Log.e("Credits", "Failure: ", t)
            }
        })
        return credits
    }

    companion object {
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData).apply { instance = this }
            }
    }
}