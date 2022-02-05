package com.jabirdev.delokfilm.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "tv_credits",
    primaryKeys = ["credit_id", "tv_id"],
    foreignKeys = [ForeignKey(entity = TvEntity::class,
        parentColumns = ["tv_id"],
        childColumns = ["tv_id"])],
    indices = [Index(value = ["credit_id"]),
    Index(value = ["tv_id"])])
data class CreditsTvEntity(

    @NonNull
    @ColumnInfo(name = "credit_id")
    var creditId: Int,

    @NonNull
    @ColumnInfo(name = "tv_id")
    var tvId: Int,

    @ColumnInfo(name= "name")
    var name: String,

    @ColumnInfo(name = "profile_path")
    var profilePath: String?,

    @ColumnInfo(name = "character")
    var character: String

)
