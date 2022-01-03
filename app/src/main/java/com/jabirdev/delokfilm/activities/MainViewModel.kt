package com.jabirdev.delokfilm.activities

import androidx.lifecycle.ViewModel
import com.jabirdev.delokfilm.models.DataModel

class MainViewModel : ViewModel() {

    var dataModel = mutableListOf<DataModel>()

    fun setData(data: DataModel){
        dataModel.add(data)
    }

}