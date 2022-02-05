package com.jabirdev.delokfilm.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.jabirdev.delokfilm.data.source.local.entity.*
import com.jabirdev.delokfilm.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao){

    fun getPopularMovies(): DataSource.Factory<Int, MovieEntity> = movieDao.getPopularMovies()

    fun getPopularTv(): DataSource.Factory<Int, TvEntity> = movieDao.getPopularTv()

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = movieDao.getFavoriteMovie()

    fun getFavoriteTv(): DataSource.Factory<Int, TvEntity> = movieDao.getFavoriteTv()

    fun getMovieWithCredit(id: Int): LiveData<MovieWithCredit> = movieDao.getMovieWithCredit(id)

    fun getTvWithCredit(id: Int): LiveData<TvWithCredit> = movieDao.getTvWithCredit(id)

    fun getDetailMovie(id: Int): LiveData<MovieEntity> = movieDao.getDetailMovie(id)

    fun getDetailTv(id: Int): LiveData<TvEntity> = movieDao.getDetailTv(id)

    fun getCreditByMovieId(id: Int): LiveData<List<CreditsEntity>> = movieDao.getCreditByMovieId(id)

    fun getCreditByTvId(id: Int): LiveData<List<CreditsTvEntity>> = movieDao.getCreditByTvId(id)

    fun insertPopularMovies(movies: List<MovieEntity>) = movieDao.insertPopularMovies(movies)

    fun insertPopularTv(tv: List<TvEntity>) = movieDao.insertPopularTv(tv)

    fun insertDetailMovie(movie: MovieEntity) = movieDao.insertDetailMovie(movie)

    fun insertDetailTv(tv: TvEntity) = movieDao.insertDetailTv(tv)

    fun insertMovieCredit(credit: List<CreditsEntity>) = movieDao.insertMovieCredit(credit)

    fun insertTvCredit(credit: List<CreditsTvEntity>) = movieDao.insertTvCredit(credit)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean){
        movie.favorite = newState
        movieDao.updateMovie(movie)
    }

    fun setFavoriteTv(tv: TvEntity, newState: Boolean){
        tv.favorite = newState
        movieDao.updateTv(tv)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

}