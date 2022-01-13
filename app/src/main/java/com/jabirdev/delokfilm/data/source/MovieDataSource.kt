package com.jabirdev.delokfilm.data.source

import androidx.lifecycle.LiveData
import com.jabirdev.delokfilm.data.*

interface MovieDataSource {

    fun getPopularMovie() : LiveData<List<MovieEntity>>

    fun getPopularTv() : LiveData<List<TvEntity>>

    fun getDetailMovie(id: String) : LiveData<DetailMovieEntity>

    fun getDetailTv(id: String) : LiveData<DetailTvEntity>

    fun getCredits(path: String, id: String) : LiveData<List<CreditsEntity>>

}