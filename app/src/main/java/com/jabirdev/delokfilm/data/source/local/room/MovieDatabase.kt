package com.jabirdev.delokfilm.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jabirdev.delokfilm.data.source.local.entity.CreditsEntity
import com.jabirdev.delokfilm.data.source.local.entity.CreditsTvEntity
import com.jabirdev.delokfilm.data.source.local.entity.MovieEntity
import com.jabirdev.delokfilm.data.source.local.entity.TvEntity


@Database(
    entities = [MovieEntity::class, TvEntity::class, CreditsEntity::class, CreditsTvEntity::class],
    version = 2,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase(){

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java,
                    "Delokfilm.db"
                ).fallbackToDestructiveMigration().build().apply {
                    INSTANCE = this
                }
            }

    }

}