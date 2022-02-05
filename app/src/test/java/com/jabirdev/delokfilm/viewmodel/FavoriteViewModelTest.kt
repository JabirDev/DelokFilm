package com.jabirdev.delokfilm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jabirdev.delokfilm.data.MovieRepository
import com.jabirdev.delokfilm.data.source.local.entity.MovieEntity
import com.jabirdev.delokfilm.data.source.local.entity.TvEntity
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
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerTv: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var pagedMovieList: PagedList<MovieEntity>

    @Mock
    private lateinit var pagedTvList: PagedList<TvEntity>

    @Before
    fun setUp(){
        viewModel = FavoriteViewModel(movieRepository)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyData = pagedMovieList
        `when`(dummyData.size).thenReturn(6)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyData

        `when`(movieRepository.getFavoriteMovie()).thenReturn(movies)
        val movieEntities = viewModel.getFavoriteMovie().value
        verify(movieRepository).getFavoriteMovie()
        assertNotNull(movieEntities)
        assertEquals(6, movieEntities?.size)

        viewModel.getFavoriteMovie().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyData)
    }

    @Test
    fun getFavoriteTv() {
        val dummyData = pagedTvList
        `when`(dummyData.size).thenReturn(6)
        val movies = MutableLiveData<PagedList<TvEntity>>()
        movies.value = dummyData

        `when`(movieRepository.getFavoriteTv()).thenReturn(movies)
        val movieEntities = viewModel.getFavoriteTv().value
        verify(movieRepository).getFavoriteTv()
        assertNotNull(movieEntities)
        assertEquals(6, movieEntities?.size)

        viewModel.getFavoriteTv().observeForever(observerTv)
        verify(observerTv).onChanged(dummyData)
    }
}