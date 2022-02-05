package com.jabirdev.delokfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jabirdev.delokfilm.data.MovieRepository
import com.jabirdev.delokfilm.data.source.local.entity.CreditsEntity
import com.jabirdev.delokfilm.data.source.local.entity.MovieWithCredit
import com.jabirdev.delokfilm.vo.Resource

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val movieId = MutableLiveData<String>()

    fun setSelectedMovie(id: String) {
        this.movieId.value = id
    }

    var moviesWithCredits: LiveData<Resource<MovieWithCredit>> = Transformations.switchMap(movieId) { mId ->
        movieRepository.getMovieWithCredit(mId)
    }

    var credits: LiveData<Resource<List<CreditsEntity>>> = Transformations.switchMap(movieId) { mId ->
        movieRepository.getAllMovieCredit(mId)
    }

    fun setFavorite(){
        val movieCredit = moviesWithCredits.value
        if (movieCredit != null){
            val movieWithCredit = movieCredit.data
            if (movieWithCredit != null){
                val movieEntity = movieWithCredit.movieEntity
                val newState = !movieEntity.favorite
                movieRepository.setFavoriteMovie(movieEntity, newState)
            }
        }
    }

}