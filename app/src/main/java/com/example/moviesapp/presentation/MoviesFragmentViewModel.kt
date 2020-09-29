package com.example.moviesapp.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.domain.NowPlayingMoviesUseCase
import com.example.moviesapp.domain.SearchMovieUseCase
import javax.inject.Inject

class MoviesFragmentViewModel @Inject constructor(
    private val nowPlayingMoviesUseCase: NowPlayingMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) :
    ViewModel() {



//    init {
//        getNowPlayingMovies()
//    }

    fun getNowPlayingMovies(): LiveData<PagedList<Movie>> {
        return nowPlayingMoviesUseCase.getMovies()
    }

    fun isListEmpty() = nowPlayingMoviesUseCase.isListEmpty()

    fun getNetworkState() = nowPlayingMoviesUseCase.getNetworkState()


    fun search(movieName: String): LiveData<PagedList<Movie>> {
        return if (movieName.isNotEmpty())
            searchMovieUseCase.search(query = movieName)
        else
            getNowPlayingMovies()
    }
}