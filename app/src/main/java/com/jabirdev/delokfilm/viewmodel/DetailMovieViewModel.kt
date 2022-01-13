package com.jabirdev.delokfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jabirdev.delokfilm.app.TMDBConstants
import com.jabirdev.delokfilm.data.CreditsEntity
import com.jabirdev.delokfilm.data.DetailMovieEntity
import com.jabirdev.delokfilm.data.source.MovieRepository

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getDetailMovie(id: String) : LiveData<DetailMovieEntity> = movieRepository.getDetailMovie(id)

    fun getCredits(id: String) : LiveData<List<CreditsEntity>> = movieRepository.getCredits(TMDBConstants.PATH_MOVIES, id)

}