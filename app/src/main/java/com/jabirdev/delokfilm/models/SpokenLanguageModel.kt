package com.jabirdev.delokfilm.models

import com.google.gson.annotations.SerializedName

data class SpokenLanguageModel(
    @SerializedName("english_name")
    val englishName: String,

    @SerializedName("iso_639_1")
    val iso639: String,

    @SerializedName("name")
    val name: String
)
