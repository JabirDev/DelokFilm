package com.jabirdev.delokfilm.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreditsDataModel(
    @SerializedName("cast")
    val cast: List<CastModel>
) {
    data class CastModel(
        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("profile_path")
        val profilePath: String?,

        @SerializedName("character")
        val character: String
    )
}
