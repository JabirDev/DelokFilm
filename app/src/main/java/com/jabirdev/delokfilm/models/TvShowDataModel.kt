package com.jabirdev.delokfilm.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class TvShowDataModel(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<TVShowResult>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int

) {

    @Parcelize
    data class TVShowResult(
        @SerializedName("backdrop_path")
        val backdropPath: String?,

        @SerializedName("first_air_date")
        val adult: String,

        @SerializedName("genre_ids")
        val genreIds: List<Int>,

        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("origin_country")
        val originCountry: List<String>,

        @SerializedName("original_language")
        val originalLanguage: String,

        @SerializedName("original_name")
        val originalName: String,

        @SerializedName("overview")
        val overview: String,

        @SerializedName("popularity")
        val popularity: Float,

        @SerializedName("poster_path")
        val posterPath: String,

        @SerializedName("vote_average")
        val voteAverage: Float,

        @SerializedName("vote_count")
        val voteCount: Int

    ) : Parcelable

}
