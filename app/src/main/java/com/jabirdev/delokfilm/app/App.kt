package com.jabirdev.delokfilm.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.jabirdev.delokfilm.utils.ThemeUtil

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val theme = ThemeUtil(this)
        if (theme.getActiveTheme() != 0){
            AppCompatDelegate.setDefaultNightMode(theme.getActiveTheme())
        }
    }

}