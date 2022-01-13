package com.jabirdev.delokfilm.data.source.remote

import com.jabirdev.delokfilm.models.*
import com.jabirdev.delokfilm.network.ApiConfig
import com.jabirdev.delokfilm.utils.EspressoIdlingResources
import com.jabirdev.delokfilm.utils.JsonHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getPopularMovie(resultListener: JsonHelper.ResultListener<MovieDataModel>) {
        EspressoIdlingResources.increment()
        val data = ApiConfig.getService().getPopularMovie()
        data.enqueue(object : Callback<MovieDataModel> {
            override fun onResponse(
                call: Call<MovieDataModel>,
                response: Response<MovieDataModel>
            ) {
                if (response.isSuccessful){
                    resultListener.onSuccess(response.body())
                }
                if (response.errorBody() != null){
                    resultListener.onError(response.errorBody())
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<MovieDataModel>, t: Throwable) {
                resultListener.onFailure(t)
            }
        })
    }

    fun getPopularTvShow(resultListener: JsonHelper.ResultListener<TvShowDataModel>) {
        EspressoIdlingResources.increment()
        val data = ApiConfig.getService().getPopularTv()
        data.enqueue(object : Callback<TvShowDataModel>{
            override fun onResponse(
                call: Call<TvShowDataModel>,
                response: Response<TvShowDataModel>
            ) {
                if (response.isSuccessful){
                    resultListener.onSuccess(response.body())
                }
                if (response.errorBody() != null){
                    resultListener.onError(response.errorBody())
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<TvShowDataModel>, t: Throwable) {
                resultListener.onFailure(t)
            }
        })
    }

    fun getDetailMovie(id: String, resultListener: JsonHelper.ResultListener<DetailMovieModel>) {
        EspressoIdlingResources.increment()
        val data = ApiConfig.getService().getDetailMovie(id)
        data.enqueue(object : Callback<DetailMovieModel>{
            override fun onResponse(
                call: Call<DetailMovieModel>,
                response: Response<DetailMovieModel>
            ) {
                if (response.isSuccessful){
                    resultListener.onSuccess(response.body())
                }
                if (response.errorBody() != null){
                    resultListener.onError(response.errorBody())
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailMovieModel>, t: Throwable) {
                resultListener.onFailure(t)
            }
        })
    }

    fun getDetailTvShow(id: String, resultListener: JsonHelper.ResultListener<DetailTvShowModel>) {
        EspressoIdlingResources.increment()
        val data = ApiConfig.getService().getDetailTvShow(id)
        data.enqueue(object : Callback<DetailTvShowModel>{
            override fun onResponse(
                call: Call<DetailTvShowModel>,
                response: Response<DetailTvShowModel>
            ) {
                if (response.isSuccessful){
                    resultListener.onSuccess(response.body())
                }
                if (response.errorBody() != null){
                    resultListener.onError(response.errorBody())
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailTvShowModel>, t: Throwable) {
                resultListener.onFailure(t)
            }
        })
    }

    fun getCredits(path: String, id: String, resultListener: JsonHelper.ResultListener<CreditsDataModel>) {
        EspressoIdlingResources.increment()
        val data = ApiConfig.getService().getCast(path, id)
        data.enqueue(object : Callback<CreditsDataModel>{
            override fun onResponse(
                call: Call<CreditsDataModel>,
                response: Response<CreditsDataModel>
            ) {
                if (response.isSuccessful){
                    resultListener.onSuccess(response.body())
                }
                if (response.errorBody() != null){
                    resultListener.onError(response.errorBody())
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<CreditsDataModel>, t: Throwable) {
                resultListener.onFailure(t)
            }
        })
    }

    companion object {
        private var instance: RemoteDataSource? = null

        fun getInstance() : RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource().apply { instance = this }
            }

    }

}