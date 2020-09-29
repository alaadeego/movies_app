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
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesFragmentViewModel @Inject constructor(
    private val nowPlayingMoviesUseCase: NowPlayingMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase) :
    ViewModel() {

    private val moviesLiveData = MutableLiveData<PagedList<Movie>>()

    fun getMovieLiveData() = moviesLiveData

    init {
        getNowPlayingMovies()
    }

    fun getNowPlayingMovies() :LiveData<PagedList<Movie>>{
        return nowPlayingMoviesUseCase.getMovies()
    }

    fun isListEmpty() = nowPlayingMoviesUseCase.isListEmpty()

    fun getNetworkState() = nowPlayingMoviesUseCase.getNetworkState()


    fun search(movieName: String) {
        if (movieName.isNotEmpty())
            viewModelScope.launch {
                val results = kotlin.runCatching {
                    searchMovieUseCase.search(page = 1, query = movieName)
                }
                results.onSuccess {
                    //  moviesLiveData.postValue(it)
                }
                results.onFailure {

                }
            }
        else
            getNowPlayingMovies()
    }
}