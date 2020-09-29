package com.example.moviesapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.NetworkState
import com.example.moviesapp.domain.NowPlayingMoviesUseCase
import com.example.moviesapp.domain.SearchMovieUseCase
import javax.inject.Inject

class MoviesFragmentViewModel @Inject constructor(
    private val nowPlayingMoviesUseCase: NowPlayingMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) :
    ViewModel() {

    val nowPlayingMoviePagedList: LiveData<PagedList<Movie>> by lazy {
        getNowPlayingMovies()
    }


    fun getNowPlayingMovies(): LiveData<PagedList<Movie>> {
        return nowPlayingMoviesUseCase.getMovies()
    }

    fun isListEmpty() = nowPlayingMoviesUseCase.isListEmpty()

    fun getNetworkState(): LiveData<NetworkState> = nowPlayingMoviesUseCase.getNetworkState()


    fun search(movieName: String): LiveData<PagedList<Movie>> {
        return if (movieName.isNotEmpty())
            searchMovieUseCase.search(query = movieName)
        else
            getNowPlayingMovies()
    }
}

