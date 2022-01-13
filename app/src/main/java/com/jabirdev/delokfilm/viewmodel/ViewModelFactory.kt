package com.jabirdev.delokfilm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jabirdev.delokfilm.data.source.MovieRepository
import com.jabirdev.delokfilm.di.Injection

class ViewModelFactory private constructor(private val movieRepository: MovieRepository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                return MainViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(PopularMovieViewModel::class.java) -> {
                return PopularMovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(PopularTvViewModel::class.java) -> {
                return PopularTvViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                return DetailMovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvViewModel::class.java) -> {
                return DetailTvViewModel(movieRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")

        }
    }

    companion object {
        private var instance: ViewModelFactory? = null
        fun getInstance() : ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository()).apply {
                    instance = this
                }
            }
    }

}