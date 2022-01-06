package com.jabirdev.delokfilm.activities

import com.jabirdev.delokfilm.utils.GetJson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private val json = GetJson.json
    private val dataModel = GetJson.getDummyData()

    @Before
    fun setUp(){
        viewModel = MainViewModel()
    }

    @Test
    fun getJson() {
        val getData = viewModel.getJson(json)
        assertNotNull(getData)
        assertEquals(dataModel, getData)
        assertEquals(10, getData.movies.size)
        assertEquals(10, getData.tvShows.size)
    }
}