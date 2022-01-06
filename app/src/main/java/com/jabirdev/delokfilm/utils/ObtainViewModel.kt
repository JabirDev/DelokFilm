package com.jabirdev.delokfilm.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jabirdev.delokfilm.activities.MainViewModel

object ObtainViewModel {

    fun main(activity: AppCompatActivity) : MainViewModel {
        val factory = ViewModelFactory.getInstance(activity)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

}