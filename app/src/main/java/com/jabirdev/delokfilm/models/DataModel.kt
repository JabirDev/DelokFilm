package com.jabirdev.delokfilm.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class DataModel(
    @SerializedName("movies")
    val movies: MutableList<Data>,
    @SerializedName("tv_shows")
    val tvShows: MutableList<Data>
) {
    @Parcelize
    data class Data(
        @SerializedName("poster")
        val poster: String,
        @SerializedName("rating")
        val rating: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("releaseDate")
        val releaseDate: String,
        @SerializedName("userScore")
        val userScore: Float,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("casts")
        val casts: List<Casts>? = null,
        @SerializedName("trailer")
        var trailer: String? = null
    ): Parcelable

    @Parcelize
    data class Casts(
        @SerializedName("name")
        val name: String,
        @SerializedName("character")
        val character: String,
        @SerializedName("photo")
        val photo: String
    ) : Parcelable
}
