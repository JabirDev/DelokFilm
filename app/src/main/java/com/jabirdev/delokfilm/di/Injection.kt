package com.jabirdev.delokfilm.di

import android.content.Context
import com.jabirdev.delokfilm.data.MovieRepository
import com.jabirdev.delokfilm.data.source.local.LocalDataSource
import com.jabirdev.delokfilm.data.source.local.room.MovieDatabase
import com.jabirdev.delokfilm.data.source.remote.RemoteDataSource
import com.jabirdev.delokfilm.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): MovieRepository {

        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

}