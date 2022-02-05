package com.jabirdev.delokfilm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jabirdev.delokfilm.data.MovieRepository
import com.jabirdev.delokfilm.data.source.local.entity.TvWithCredit
import com.jabirdev.delokfilm.utils.GetJson
import com.jabirdev.delokfilm.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvViewModelTest {

    private lateinit var viewModel: DetailTvViewModel
    private val tvId = 77169

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<TvWithCredit>>

    @Before
    fun setUp(){
        viewModel = DetailTvViewModel(movieRepository)
        viewModel.setSelectedTv(tvId.toString())
    }

    @Test
    fun getDetailTv() {
        val dummyData = Resource.success(GetJson.getDetailTv())
        val tv = MutableLiveData<Resource<TvWithCredit>>()
        tv.value = dummyData

        `when`(movieRepository.getTvWithCredit(tvId.toString())).thenReturn(tv)

        val tvEntities = viewModel.tvWithCredits

        viewModel.tvWithCredits.observeForever(observer)
        verify(observer).onChanged(dummyData)

        assertNotNull(tvEntities)
        assertEquals(tvId, tvEntities.value?.data?.tvEntity?.id)
    }

}