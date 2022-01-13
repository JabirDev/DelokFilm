package com.jabirdev.delokfilm.viewmodel

import androidx.lifecycle.ViewModel
import com.jabirdev.delokfilm.data.source.MovieRepository

class PopularMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getPopularMovies() = movieRepository.getPopularMovie()

}