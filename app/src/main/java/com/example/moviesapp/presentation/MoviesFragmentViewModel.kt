package com.example.moviesapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.domain.NowPlayingMoviesUseCase
import com.example.moviesapp.domain.SearchMovieUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesFragmentViewModel @Inject constructor(
    private val nowPlayingMoviesUseCase: NowPlayingMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) :
    ViewModel() {

    private val moviesLiveData = MutableLiveData<List<Movie>>()

    fun getMovieLiveData() = moviesLiveData

    init {
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            val results = kotlin.runCatching {
                nowPlayingMoviesUseCase.getMovies(page = 1)
            }
            results.onSuccess {
                moviesLiveData.postValue(it)
            }
            results.onFailure {

            }
        }
    }


    fun search(movieName: String) {
        viewModelScope.launch {
            val results = kotlin.runCatching {
                searchMovieUseCase.search(page = 1,query = movieName)
            }
            results.onSuccess {
                moviesLiveData.postValue(it)
            }
            results.onFailure {

            }
        }

    }
}