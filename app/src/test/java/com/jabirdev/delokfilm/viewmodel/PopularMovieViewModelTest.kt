package com.jabirdev.delokfilm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jabirdev.delokfilm.data.MovieRepository
import com.jabirdev.delokfilm.data.source.local.entity.MovieEntity
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
class PopularMovieViewModelTest {

    private lateinit var viewModel: PopularMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp(){
        viewModel = PopularMovieViewModel(movieRepository)
    }

    @Test
    fun getPopularMovies() {
        val mpd = Resource.success(pagedList)
        `when`(mpd.data?.size).thenReturn(6)

        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = mpd

        `when`(movieRepository.getPopularMovie()).thenReturn(movies)
        val movieEntities = viewModel.getPopularMovies().value?.data
        verify(movieRepository).getPopularMovie()
        assertNotNull(movieEntities)
        assertEquals(6, movieEntities?.size)

        viewModel.getPopularMovies().observeForever(observer)
        verify(observer).onChanged(mpd)

    }
}