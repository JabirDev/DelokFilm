package com.jabirdev.delokfilm.models

import com.google.gson.annotations.SerializedName

data class ProductionCountriesModel(
    @SerializedName("iso_3166_1")
    val iso3166: String,

    @SerializedName("name")
    val name: String
)
