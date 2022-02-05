package com.jabirdev.delokfilm.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TvWithCredit(
    @Embedded
    var tvEntity: TvEntity,

    @Relation(parentColumn = "tv_id", entityColumn = "tv_id")
    var credits: List<CreditsTvEntity>
)
