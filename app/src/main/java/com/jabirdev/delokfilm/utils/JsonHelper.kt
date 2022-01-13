package com.jabirdev.delokfilm.utils

import com.jabirdev.delokfilm.models.*
import com.jabirdev.delokfilm.network.ApiConfig
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object JsonHelper {

    fun loadPopularMovie(resultListener: ResultListener<MovieDataModel>) {
        val data = ApiConfig.getService().getPopularMovie()
        data.enqueue(object : Callback<MovieDataModel>{
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
            }

            override fun onFailure(call: Call<MovieDataModel>, t: Throwable) {
                resultListener.onFailure(t)
            }
        })
    }

    fun loadPopularTv(resultListener: ResultListener<TvShowDataModel>) {
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
            }

            override fun onFailure(call: Call<TvShowDataModel>, t: Throwable) {
                resultListener.onFailure(t)
            }
        })
    }

    fun loadDetailMovie(id: String, resultListener: ResultListener<DetailMovieModel>){
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
            }

            override fun onFailure(call: Call<DetailMovieModel>, t: Throwable) {
                resultListener.onFailure(t)
            }
        })
    }

    fun loadDetailTv(id: String, resultListener: ResultListener<DetailTvShowModel>){
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
            }

            override fun onFailure(call: Call<DetailTvShowModel>, t: Throwable) {
                resultListener.onFailure(t)
            }
        })
    }

    fun loadCast(path: String, id: String, resultListener: ResultListener<CreditsDataModel>){
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
            }

            override fun onFailure(call: Call<CreditsDataModel>, t: Throwable) {
                resultListener.onFailure(t)
            }
        })
    }

    interface ResultListener<S>{
        fun onSuccess(successModel: S?)
        fun onError(error: ResponseBody?)
        fun onFailure(t: Throwable?)
    }

}