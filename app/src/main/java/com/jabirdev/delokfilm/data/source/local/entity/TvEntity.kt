package com.jabirdev.delokfilm.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_entities")
data class TvEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tv_id")
    var id: Int,

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: Float,

    @ColumnInfo(name = "poster_path")
    var posterPath: String,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Float,

    @ColumnInfo(name = "vote_count")
    var voteCount: Int,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)
