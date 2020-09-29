package com.example.moviesapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviesapp.TestCoroutineRule
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.NetworkState
import com.example.moviesapp.domain.NowPlayingMoviesUseCase
import com.example.moviesapp.domain.SearchMovieUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class MoviesFragmentViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var nowPlayingMoviesUseCase: NowPlayingMoviesUseCase

    private lateinit var searchMovieUseCase: SearchMovieUseCase

    private lateinit var moviesViewModel: MoviesFragmentViewModel

    private lateinit var nowPlayingMovieObserver: Observer<PagedList<Movie>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        nowPlayingMoviesUseCase = mock()

        searchMovieUseCase = mock()

        nowPlayingMovieObserver = mock()

        moviesViewModel =
            MoviesFragmentViewModel(nowPlayingMoviesUseCase, searchMovieUseCase)
    }

    @Test
    fun `when user get now playing movies then network state changed to Loaded`() =
        testCoroutineRule.runBlockingTest {

            // Given
            whenever(moviesViewModel.getNetworkState()).thenReturn(MutableLiveData(NetworkState.LOADED))
            whenever(moviesViewModel.getNowPlayingMovies()).thenReturn(MutableLiveData<PagedList<Movie>>())

            // when
            moviesViewModel.getNowPlayingMovies()

            // assert
            assertEquals(
                moviesViewModel.getNetworkState().value,
                NetworkState.LOADED
            )
        }


    @Test
    fun `when user search movie then network state changed to Loaded`() =
        testCoroutineRule.runBlockingTest {

            // Given
            whenever(moviesViewModel.getNetworkState()).thenReturn(MutableLiveData(NetworkState.LOADED))
            whenever(moviesViewModel.search("")).thenReturn(MutableLiveData<PagedList<Movie>>())

            // when
            moviesViewModel.search("The lord of the rings")

            // assert
            assertEquals(
                moviesViewModel.getNetworkState().value,
                NetworkState.LOADED
            )
        }
}

