package com.jabirdev.delokfilm.di

import com.jabirdev.delokfilm.data.source.MovieRepository
import com.jabirdev.delokfilm.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(): MovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieRepository.getInstance(remoteDataSource)
    }

}