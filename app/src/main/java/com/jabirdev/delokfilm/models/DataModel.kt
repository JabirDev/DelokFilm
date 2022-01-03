package com.jabirdev.delokfilm.models

data class DataModel(
    val poster: Int,
    val rating: String,
    val title: String,
    val releaseDate: String,
    val userScore: Float,
    val overview: String,
    val casts: MutableList<Casts>? = null,
    var trailer: String? = null
) {
    data class Casts(
        val actorName: String,
        val characterName: String,
        val photo: Int
    )
}
