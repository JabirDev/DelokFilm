package com.jabirdev.delokfilm.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jabirdev.delokfilm.viewmodel.*

object ObtainViewModel {

    fun popularMovie(activity: AppCompatActivity) : PopularMovieViewModel {
        val factory = ViewModelFactory.getInstance(activity)
        return ViewModelProvider(activity, factory)[PopularMovieViewModel::class.java]
    }

    fun popularTv(activity: AppCompatActivity) : PopularTvViewModel {
        val factory = ViewModelFactory.getInstance(activity)
        return ViewModelProvider(activity, factory)[PopularTvViewModel::class.java]
    }

    fun detailMovie(activity: AppCompatActivity) : DetailMovieViewModel {
        val factory = ViewModelFactory.getInstance(activity)
        return ViewModelProvider(activity, factory)[DetailMovieViewModel::class.java]
    }

    fun detailTv(activity: AppCompatActivity) : DetailTvViewModel {
        val factory = ViewModelFactory.getInstance(activity)
        return ViewModelProvider(activity, factory)[DetailTvViewModel::class.java]
    }

    fun favorite(activity: AppCompatActivity) : FavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity)
        return ViewModelProvider(activity, factory)[FavoriteViewModel::class.java]
    }

}