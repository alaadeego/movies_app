package com.example.moviesapp.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviesapp.data.model.Movie

interface NowPlayingMoviesUseCase {
    suspend fun getMovies(): LiveData<PagedList<Movie>>
}