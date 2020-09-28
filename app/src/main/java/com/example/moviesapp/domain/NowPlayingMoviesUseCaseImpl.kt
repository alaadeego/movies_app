package com.example.moviesapp.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.repository.NowPlayingMoviePagedListRepository

class NowPlayingMoviesUseCaseImpl constructor(private val repository: NowPlayingMoviePagedListRepository) :
    NowPlayingMoviesUseCase {
    override suspend fun getMovies(): LiveData<PagedList<Movie>> {
        return repository.fetchNowPlayedMoviePagedList()
    }
}