package com.jabirdev.delokfilm.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.jabirdev.delokfilm.data.source.local.entity.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_entities")
    fun getPopularMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_entities")
    fun getPopularTv(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM movie_entities where favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_entities where favorite = 1")
    fun getFavoriteTv(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM movie_entities where movie_id = :id")
    fun getDetailMovie(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tv_entities where tv_id = :id")
    fun getDetailTv(id: Int): LiveData<TvEntity>

    @Transaction
    @Query("SELECT * FROM movie_entities WHERE movie_id = :id")
    fun getMovieWithCredit(id: Int): LiveData<MovieWithCredit>

    @Transaction
    @Query("SELECT * FROM tv_entities WHERE tv_id = :id")
    fun getTvWithCredit(id: Int): LiveData<TvWithCredit>

    @Query("SELECT * FROM movie_credits WHERE movie_id = :id")
    fun getCreditByMovieId(id: Int): LiveData<List<CreditsEntity>>

    @Query("SELECT * FROM tv_credits WHERE tv_id = :id")
    fun getCreditByTvId(id: Int): LiveData<List<CreditsTvEntity>>

    @Update
    fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularTv(tv: List<TvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailTv(tv: TvEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieCredit(credit: List<CreditsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvCredit(credit: List<CreditsTvEntity>)

    @Update
    fun updateTv(tv: TvEntity)

}