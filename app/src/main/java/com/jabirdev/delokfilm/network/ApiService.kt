package com.jabirdev.delokfilm.network

import com.jabirdev.delokfilm.app.TMDBConstants.PATH_MOVIES
import com.jabirdev.delokfilm.app.TMDBConstants.PATH_POPULAR_MOVIES
import com.jabirdev.delokfilm.app.TMDBConstants.PATH_POPULAR_TV_SHOW
import com.jabirdev.delokfilm.app.TMDBConstants.PATH_TV_SHOW
import com.jabirdev.delokfilm.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(PATH_POPULAR_MOVIES)
    fun getPopularMovie() : Call<MovieDataModel>

    @GET(PATH_POPULAR_TV_SHOW)
    fun getPopularTv() : Call<TvShowDataModel>

    @GET("$PATH_MOVIES{id}")
    fun getDetailMovie(@Path("id") id: String) : Call<DetailMovieModel>

    @GET("$PATH_TV_SHOW{id}")
    fun getDetailTvShow(@Path("id") id: String) : Call<DetailTvShowModel>

    @GET("{path}{id}/credits")
    fun getCast(
        @Path("path") path: String,
        @Path("id") id: String
    ) : Call<CreditsDataModel>

}