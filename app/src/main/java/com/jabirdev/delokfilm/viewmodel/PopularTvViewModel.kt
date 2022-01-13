package com.jabirdev.delokfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jabirdev.delokfilm.data.TvEntity
import com.jabirdev.delokfilm.data.source.MovieRepository

class PopularTvViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getPopularTv() : LiveData<List<TvEntity>> = movieRepository.getPopularTv()

}