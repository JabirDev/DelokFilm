package com.jabirdev.delokfilm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jabirdev.delokfilm.data.MovieEntity
import com.jabirdev.delokfilm.data.source.MovieRepository
import com.jabirdev.delokfilm.utils.GetJson
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
class PopularMovieViewModelTest {

    private lateinit var viewModel: PopularMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp(){
        viewModel = PopularMovieViewModel(movieRepository)
    }

    @Test
    fun getPopularMovies() {
        val dummyMovie = GetJson.getPopularMovies()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovie

        `when`(movieRepository.getPopularMovie()).thenReturn(movies)
        val movieEntity = viewModel.getPopularMovies().value
        verify(movieRepository).getPopularMovie()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.size)

        viewModel.getPopularMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}