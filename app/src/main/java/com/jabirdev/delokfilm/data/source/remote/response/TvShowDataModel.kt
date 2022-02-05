package com.jabirdev.delokfilm.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class TvShowDataModel(
    @SerializedName("results")
    val results: List<TVShowResult>
) {

    @Parcelize
    data class TVShowResult(

        @SerializedName("id")
        val id: Int,

        @SerializedName("first_air_date")
        val firstAirDate: String,

        @SerializedName("name")
        val name: String,

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
