package com.jabirdev.delokfilm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jabirdev.delokfilm.data.source.local.entity.TvEntity
import com.jabirdev.delokfilm.data.MovieRepository
import com.jabirdev.delokfilm.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PopularTvViewModelTest {

    private lateinit var viewModel: PopularTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Before
    fun setUp(){
        viewModel = PopularTvViewModel(movieRepository)
    }

    @Test
    fun getPopularTv() {
        val mpd = Resource.success(pagedList)
        `when`(mpd.data?.size).thenReturn(6)

        val tv = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tv.value = mpd

        `when`(movieRepository.getPopularTv()).thenReturn(tv)
        val tvEntities = viewModel.getPopularTv().value?.data
        verify(movieRepository).getPopularTv()
        assertNotNull(tvEntities)
        assertEquals(6, tvEntities?.size)

        viewModel.getPopularTv().observeForever(observer)
        verify(observer).onChanged(mpd)

    }
}