package com.jabirdev.delokfilm.app

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.jabirdev.delokfilm.utils.ThemeUtil

class App : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        applicationContext()
        val theme = ThemeUtil(this)
        if (theme.getActiveTheme() != 0){
            AppCompatDelegate.setDefaultNightMode(theme.getActiveTheme())
        } else {
            theme.changeTheme(AppCompatDelegate.MODE_NIGHT_YES)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    companion object {
        private var instance: App? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

}