package com.jabirdev.delokfilm.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entities")
data class MovieEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movie_id")
    var id: Int,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: Float,

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Float,

    @ColumnInfo(name = "vote_count")
    var voteCount: Int,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)