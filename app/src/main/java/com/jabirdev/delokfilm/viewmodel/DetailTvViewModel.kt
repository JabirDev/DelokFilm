package com.jabirdev.delokfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jabirdev.delokfilm.app.TMDBConstants
import com.jabirdev.delokfilm.data.CreditsEntity
import com.jabirdev.delokfilm.data.DetailTvEntity
import com.jabirdev.delokfilm.data.source.MovieRepository

class DetailTvViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getDetailTv(id: String) : LiveData<DetailTvEntity> = movieRepository.getDetailTv(id)

    fun getCredits(id: String) : LiveData<List<CreditsEntity>> = movieRepository.getCredits(TMDBConstants.PATH_TV_SHOW, id)

}