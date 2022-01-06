package com.jabirdev.delokfilm.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jabirdev.delokfilm.activities.MainViewModel

class ViewModelFactory private constructor(private val activity: AppCompatActivity) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(activity: AppCompatActivity): ViewModelFactory {
            if (INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(activity)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

}