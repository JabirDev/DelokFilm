package com.jabirdev.delokfilm.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "movie_credits",
    primaryKeys = ["credit_id", "movie_id"],
    foreignKeys = [ForeignKey(entity = MovieEntity::class,
        parentColumns = ["movie_id"],
        childColumns = ["movie_id"])],
    indices = [Index(value = ["credit_id"]),
        Index(value = ["movie_id"])])
data class CreditsEntity(

    @NonNull
    @ColumnInfo(name = "credit_id")
    var creditId: Int,

    @NonNull
    @ColumnInfo(name = "movie_id")
    var movieId: Int,

    @ColumnInfo(name= "name")
    var name: String,

    @ColumnInfo(name = "profile_path")
    var profilePath: String?,

    @ColumnInfo(name = "character")
    var character: String

)
