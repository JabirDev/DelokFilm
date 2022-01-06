package com.jabirdev.delokfilm.activities

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.jabirdev.delokfilm.models.DataModel

class MainViewModel : ViewModel() {

    fun getJson(json: String) : DataModel {
        return Gson().fromJson(json, DataModel::class.java)
    }

}