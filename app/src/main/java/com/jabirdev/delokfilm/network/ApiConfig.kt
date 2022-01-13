package com.jabirdev.delokfilm.network

import com.jabirdev.delokfilm.BuildConfig
import com.jabirdev.delokfilm.app.TMDBConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object {
        fun getService(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                )

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val url = chain.request()
                        .url
                        .newBuilder()
                        .addQueryParameter(TMDBConstants.API_KEY, BuildConfig.TMDB_API_KEY)
                        .build()
                    val newUrl = chain.request().newBuilder().url(url).build()
                    chain.proceed(newUrl)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.TMDB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)

        }
    }

}