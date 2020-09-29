package com.example.moviesapp.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.NetworkState
import com.example.moviesapp.data.repository.NowPlayingMoviePagedListRepository

class NowPlayingMoviesUseCaseImpl constructor(private val repository: NowPlayingMoviePagedListRepository) :
    NowPlayingMoviesUseCase {


    override fun getMovies(): LiveData<PagedList<Movie>> {
        return repository.fetchNowPlayedMoviePagedList()
    }

    override fun getNetworkState(): LiveData<NetworkState> {
        return repository.getNetworkState()
    }

    override fun isListEmpty(): Boolean {
        return getMovies().value?.isEmpty() ?: true
    }
}