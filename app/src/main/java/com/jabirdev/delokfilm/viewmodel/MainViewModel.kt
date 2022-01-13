package com.jabirdev.delokfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.jabirdev.delokfilm.data.DetailMovieEntity
import com.jabirdev.delokfilm.data.DetailTvEntity
import com.jabirdev.delokfilm.data.TvEntity
import com.jabirdev.delokfilm.data.source.MovieRepository
import com.jabirdev.delokfilm.models.DataModel

class MainViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getPopularTv() : LiveData<List<TvEntity>> = movieRepository.getPopularTv()

    fun getDetailMovie(id: String) : LiveData<DetailMovieEntity> = movieRepository.getDetailMovie(id)

    fun getDetailTv(id: String) : LiveData<DetailTvEntity> = movieRepository.getDetailTv(id)

    fun getJson(json: String) : DataModel {
        return Gson().fromJson(json, DataModel::class.java)
    }

}