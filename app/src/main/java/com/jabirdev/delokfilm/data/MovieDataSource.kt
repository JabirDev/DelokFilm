package com.jabirdev.delokfilm.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jabirdev.delokfilm.data.source.local.entity.*
import com.jabirdev.delokfilm.vo.Resource

interface MovieDataSource {

    fun getPopularMovie() : LiveData<Resource<PagedList<MovieEntity>>>

    fun getPopularTv() : LiveData<Resource<PagedList<TvEntity>>>

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTv(): LiveData<PagedList<TvEntity>>

    fun getDetailMovie(id: String) : LiveData<Resource<MovieEntity>>

    fun getDetailTv(id: String) : LiveData<Resource<TvEntity>>

    fun getMovieWithCredit(id: String): LiveData<Resource<MovieWithCredit>>

    fun getTvWithCredit(id: String): LiveData<Resource<TvWithCredit>>

    fun getAllMovieCredit(id: String): LiveData<Resource<List<CreditsEntity>>>

    fun getAllTvCredit(id: String): LiveData<Resource<List<CreditsTvEntity>>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun setFavoriteTv(tv: TvEntity, state: Boolean)

}