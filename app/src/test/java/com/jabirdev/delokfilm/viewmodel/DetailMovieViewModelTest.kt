package com.jabirdev.delokfilm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jabirdev.delokfilm.data.MovieRepository
import com.jabirdev.delokfilm.data.source.local.entity.MovieWithCredit
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
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val movieId = 634649

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<MovieWithCredit>>


    @Before
    fun setUp(){
        viewModel = DetailMovieViewModel(movieRepository)
        viewModel.setSelectedMovie(movieId.toString())
    }

    @Test
    fun getDetailMovie() {
        val dummyData = Resource.success(GetJson.getDetailMovie())
        val movies = MutableLiveData<Resource<MovieWithCredit>>()
        movies.value = dummyData

        `when`(movieRepository.getMovieWithCredit(movieId.toString())).thenReturn(movies)

        val movieEntities = viewModel.moviesWithCredits

        viewModel.moviesWithCredits.observeForever(observer)
        verify(observer).onChanged(dummyData)

        assertNotNull(movieEntities)
        assertEquals(movieId, movieEntities.value?.data?.movieEntity?.id)

    }

}