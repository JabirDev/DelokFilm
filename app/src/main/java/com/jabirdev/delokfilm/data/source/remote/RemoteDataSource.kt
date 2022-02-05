package com.jabirdev.delokfilm.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jabirdev.delokfilm.data.source.remote.response.CreditsDataModel
import com.jabirdev.delokfilm.data.source.remote.response.MovieDataModel
import com.jabirdev.delokfilm.data.source.remote.response.TvShowDataModel
import com.jabirdev.delokfilm.network.ApiConfig
import com.jabirdev.delokfilm.utils.EspressoIdlingResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getPopularMovie(): LiveData<ApiResponse<List<MovieDataModel.MovieResult>>> {
        EspressoIdlingResources.increment()
        val movieResult = MutableLiveData<ApiResponse<List<MovieDataModel.MovieResult>>>()
        val data = ApiConfig.getService().getPopularMovie()
        data.enqueue(object : Callback<MovieDataModel> {
            override fun onResponse(
                call: Call<MovieDataModel>,
                response: Response<MovieDataModel>
            ) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        movieResult.value = ApiResponse.success(response.body()!!.results)
                    } else {
                        ApiResponse.empty("No popular movies", response.body())
                    }
                }
                if (response.errorBody() != null){
                    ApiResponse.error("Error", response.errorBody())
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<MovieDataModel>, t: Throwable) {
                ApiResponse.error("Failure", t)
                EspressoIdlingResources.decrement()
            }
        })
        return movieResult
    }

    fun getPopularTvShow(): LiveData<ApiResponse<List<TvShowDataModel.TVShowResult>>> {
        EspressoIdlingResources.increment()
        val tvResult = MutableLiveData<ApiResponse<List<TvShowDataModel.TVShowResult>>>()
        val data = ApiConfig.getService().getPopularTv()
        data.enqueue(object : Callback<TvShowDataModel>{
            override fun onResponse(
                call: Call<TvShowDataModel>,
                response: Response<TvShowDataModel>
            ) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        tvResult.value = ApiResponse.success(response.body()!!.results)
                    } else {
                        ApiResponse.empty("No popular tv show", response.body())
                    }
                }
                if (response.errorBody() != null){
                    ApiResponse.error("Error", response.errorBody())
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<TvShowDataModel>, t: Throwable) {
                ApiResponse.error("Failure", t)
                EspressoIdlingResources.decrement()
            }
        })
        return tvResult
    }

    fun getDetailMovie(id: String) : LiveData<ApiResponse<MovieDataModel.MovieResult>> {
        EspressoIdlingResources.increment()
        val movies = MutableLiveData<ApiResponse<MovieDataModel.MovieResult>>()
        val data = ApiConfig.getService().getDetailMovie(id)
        data.enqueue(object : Callback<MovieDataModel.MovieResult>{
            override fun onResponse(
                call: Call<MovieDataModel.MovieResult>,
                response: Response<MovieDataModel.MovieResult>
            ) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        movies.value = ApiResponse.success(response.body()!!)
                    }else {
                        ApiResponse.empty("No movies", response.body())
                    }
                }
                if (response.errorBody() != null){
                    ApiResponse.error("Error", response.errorBody())
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<MovieDataModel.MovieResult>, t: Throwable) {
                ApiResponse.error("Failure", t)
                EspressoIdlingResources.decrement()
            }
        })
        return movies
    }

    fun getDetailTvShow(id: String) : LiveData<ApiResponse<TvShowDataModel.TVShowResult>> {
        EspressoIdlingResources.increment()
        val tv = MutableLiveData<ApiResponse<TvShowDataModel.TVShowResult>>()
        val data = ApiConfig.getService().getDetailTvShow(id)
        data.enqueue(object : Callback<TvShowDataModel.TVShowResult>{
            override fun onResponse(
                call: Call<TvShowDataModel.TVShowResult>,
                response: Response<TvShowDataModel.TVShowResult>
            ) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        tv.value = ApiResponse.success(response.body()!!)
                    } else {
                        ApiResponse.empty("No tv show", response.body())
                    }
                }
                if (response.errorBody() != null){
                    ApiResponse.error("Error", response.errorBody())
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<TvShowDataModel.TVShowResult>, t: Throwable) {
                ApiResponse.error("Failure", t)
                EspressoIdlingResources.decrement()
            }
        })
        return tv
    }

    fun getCredits(path: String, id: String) : LiveData<ApiResponse<List<CreditsDataModel.CastModel>>> {
        EspressoIdlingResources.increment()
        val credit = MutableLiveData<ApiResponse<List<CreditsDataModel.CastModel>>>()
        val data = ApiConfig.getService().getCast(path, id)
        data.enqueue(object : Callback<CreditsDataModel>{
            override fun onResponse(
                call: Call<CreditsDataModel>,
                response: Response<CreditsDataModel>
            ) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        credit.value = ApiResponse.success(response.body()!!.cast)
                    } else {
                        ApiResponse.empty("No credits", response.body())
                    }
                }
                if (response.errorBody() != null){
                    ApiResponse.error("Error", response.errorBody())
                }
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<CreditsDataModel>, t: Throwable) {
                ApiResponse.error("Failure", t)
                EspressoIdlingResources.decrement()
            }
        })
        return credit
    }

    companion object {
        private var instance: RemoteDataSource? = null

        fun getInstance() : RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource().apply { instance = this }
            }

    }

}