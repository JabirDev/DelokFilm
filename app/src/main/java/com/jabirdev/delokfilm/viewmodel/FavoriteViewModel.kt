package com.jabirdev.delokfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jabirdev.delokfilm.data.MovieRepository
import com.jabirdev.delokfilm.data.source.local.entity.MovieEntity
import com.jabirdev.delokfilm.data.source.local.entity.TvEntity

class FavoriteViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = movieRepository.getFavoriteMovie()

    fun getFavoriteTv(): LiveData<PagedList<TvEntity>> = movieRepository.getFavoriteTv()

}