package com.jabirdev.delokfilm.models

import com.google.gson.annotations.SerializedName

data class CreditsDataModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("cast")
    val cast: List<CastModel>
) {
    data class CastModel(
        @SerializedName("adult")
        val adult: Boolean,

        @SerializedName("gender")
        val gender: Int,

        @SerializedName("id")
        val id: Int,

        @SerializedName("known_for_department")
        val knownForDepartment: String,

        @SerializedName("name")
        val name: String,

        @SerializedName("original_name")
        val originalName: String,

        @SerializedName("popularity")
        val popularity: Float,

        @SerializedName("profile_path")
        val profilePath: String?,

        @SerializedName("cast_id")
        val castId: Int,

        @SerializedName("character")
        val character: String,

        @SerializedName("credit_id")
        val creditId: String,

        @SerializedName("order")
        val order: Int
    )
}
