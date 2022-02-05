package com.jabirdev.delokfilm.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MovieDataModel(
    @SerializedName("results")
    val results: List<MovieResult>
) {

    @Parcelize
    data class MovieResult(
        @SerializedName("id")
        val id: Int,

        @SerializedName("overview")
        val overview: String,

        @SerializedName("popularity")
        val popularity: Float,

        @SerializedName("poster_path")
        val posterPath: String,

        @SerializedName("release_date")
        val releaseDate: String,

        @SerializedName("title")
        val title: String,

        @SerializedName("vote_average")
        val voteAverage: Float,

        @SerializedName("vote_count")
        val voteCount: Int

    ) : Parcelable

}
