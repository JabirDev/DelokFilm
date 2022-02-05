package com.jabirdev.delokfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jabirdev.delokfilm.data.MovieRepository
import com.jabirdev.delokfilm.data.source.local.entity.MovieEntity
import com.jabirdev.delokfilm.vo.Resource

class PopularMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getPopularMovie()

}