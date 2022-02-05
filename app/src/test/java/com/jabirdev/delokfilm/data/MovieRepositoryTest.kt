package com.jabirdev.delokfilm.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jabirdev.delokfilm.data.source.local.LocalDataSource
import com.jabirdev.delokfilm.data.source.local.entity.*
import com.jabirdev.delokfilm.data.source.remote.RemoteDataSource
import com.jabirdev.delokfilm.utils.AppExecutors
import com.jabirdev.delokfilm.utils.GetJson
import com.jabirdev.delokfilm.utils.LiveDataTestUtil
import com.jabirdev.delokfilm.utils.PagedListUtil
import com.jabirdev.delokfilm.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieRepository = FakeMovieRepository(remote, localDataSource, appExecutors)

    private val movieId = 634649
    private val tvId = 77169

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getPopularMovie() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getPopularMovies()).thenReturn(dataSource)
        movieRepository.getPopularMovie()

        val dummyMovie = GetJson.getListMovies()
        val movieEntities = Resource.success(PagedListUtil.mockPagedList(dummyMovie))
        verify(localDataSource).getPopularMovies()
        assertNotNull(movieEntities.data)
        assertEquals(20, movieEntities.data?.size)

    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getPopularTv() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(localDataSource.getPopularTv()).thenReturn(dataSource)
        movieRepository.getPopularTv()

        val dummyMovie = GetJson.getListTv()
        val tvEntities = Resource.success(PagedListUtil.mockPagedList(dummyMovie))
        verify(localDataSource).getPopularTv()
        assertNotNull(tvEntities.data)
        assertEquals(20, tvEntities.data?.size)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getDetailMovie() {
        val dummyData = MutableLiveData<MovieWithCredit>()
        dummyData.value = GetJson.getDetailMovie()
        `when`(localDataSource.getMovieWithCredit(movieId)).thenReturn(dummyData)

        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getMovieWithCredit(movieId.toString()))
        verify(localDataSource)
        assertNotNull(movieEntities.data)
        assertEquals(movieId, movieEntities.data?.movieEntity?.id)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getDetailTv() {
        val dummyData = MutableLiveData<TvWithCredit>()
        dummyData.value = GetJson.getDetailTv()
        `when`(localDataSource.getTvWithCredit(tvId)).thenReturn(dummyData)

        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getTvWithCredit(tvId.toString()))
        verify(localDataSource)
        assertNotNull(movieEntities.data)
        assertEquals(tvId, movieEntities.data?.tvEntity?.id)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getCreditsMovie() {
        val creditMovie = MutableLiveData<List<CreditsEntity>>()
        creditMovie.value = GetJson.getCreditMovie()
        `when`(localDataSource.getCreditByMovieId(movieId)).thenReturn(creditMovie)

        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getAllMovieCredit(movieId.toString()))

        verify(localDataSource)
        assertNotNull(movieEntities.data)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getCreditsTv() {
        val creditTv = MutableLiveData<List<CreditsTvEntity>>()
        creditTv.value = GetJson.getCreditTv()
        `when`(localDataSource.getCreditByTvId(tvId)).thenReturn(creditTv)

        val tvEntities = LiveDataTestUtil.getValue(movieRepository.getAllTvCredit(tvId.toString()))

        verify(localDataSource)
        assertNotNull(tvEntities.data)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getFavoriteMovie() {
        val movieSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getFavoriteMovie()).thenReturn(movieSource)
        movieRepository.getFavoriteMovie()

        val dummyMovie = GetJson.getListMovies()
        val movieEntities = Resource.success(PagedListUtil.mockPagedList(dummyMovie))
        verify(localDataSource).getFavoriteMovie()
        assertNotNull(movieEntities.data)
        assertEquals(20, movieEntities.data?.size)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun getFavoriteTv() {
        val tvSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(localDataSource.getFavoriteTv()).thenReturn(tvSource)
        movieRepository.getFavoriteTv()

        val dummyTv = GetJson.getListTv()
        val tvEntities = Resource.success(PagedListUtil.mockPagedList(dummyTv))
        verify(localDataSource).getFavoriteTv()
        assertNotNull(tvEntities.data)
        assertEquals(20, tvEntities.data?.size)
    }

}