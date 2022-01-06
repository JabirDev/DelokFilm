package com.jabirdev.delokfilm.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.jabirdev.delokfilm.app.AppConstants.PREF_NAME
import com.jabirdev.delokfilm.app.AppConstants.PREF_THEME

class ThemeUtil(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    private val activeTheme = sharedPreferences.getInt(PREF_THEME, 0)

    fun getActiveTheme() : Int {
        return activeTheme
    }

    fun changeTheme(mode: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(PREF_THEME, mode)
        editor.apply()
    }

}