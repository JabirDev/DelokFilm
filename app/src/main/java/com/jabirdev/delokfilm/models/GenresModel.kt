package com.jabirdev.delokfilm.models

import com.google.gson.annotations.SerializedName

data class GenresModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)
