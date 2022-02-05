package com.jabirdev.delokfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jabirdev.delokfilm.data.source.local.entity.TvEntity
import com.jabirdev.delokfilm.data.MovieRepository
import com.jabirdev.delokfilm.vo.Resource

class PopularTvViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getPopularTv() : LiveData<Resource<PagedList<TvEntity>>> = movieRepository.getPopularTv()

}