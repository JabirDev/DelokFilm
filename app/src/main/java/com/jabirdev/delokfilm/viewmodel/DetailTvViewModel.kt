package com.jabirdev.delokfilm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jabirdev.delokfilm.data.MovieRepository
import com.jabirdev.delokfilm.data.source.local.entity.CreditsTvEntity
import com.jabirdev.delokfilm.data.source.local.entity.TvWithCredit
import com.jabirdev.delokfilm.vo.Resource

class DetailTvViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val tvId = MutableLiveData<String>()

    fun setSelectedTv(id: String){
        this.tvId.value = id
    }

    var tvWithCredits: LiveData<Resource<TvWithCredit>> = Transformations.switchMap(tvId) { tId ->
        movieRepository.getTvWithCredit(tId)
    }

    var credits: LiveData<Resource<List<CreditsTvEntity>>> = Transformations.switchMap(tvId) { tId ->
        movieRepository.getAllTvCredit(tId)
    }

    fun setFavorite(){
        val tvCredit = tvWithCredits.value
        if (tvCredit != null){
            val tvWithCredit = tvCredit.data
            if (tvWithCredit != null){
                val tvEntity = tvWithCredit.tvEntity
                val newState = !tvEntity.favorite
                movieRepository.setFavoriteTv(tvEntity, newState)
            }
        }
    }

}