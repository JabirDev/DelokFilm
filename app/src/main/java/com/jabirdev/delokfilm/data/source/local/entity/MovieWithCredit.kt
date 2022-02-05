package com.jabirdev.delokfilm.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithCredit(
    @Embedded
    var movieEntity: MovieEntity,

    @Relation(parentColumn = "movie_id", entityColumn = "movie_id")
    var credits: List<CreditsEntity>
)