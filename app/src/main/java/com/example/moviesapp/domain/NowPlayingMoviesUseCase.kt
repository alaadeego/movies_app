package com.example.moviesapp.domain

import com.example.moviesapp.data.model.Movie

interface NowPlayingMoviesUseCase {
    suspend fun getMovies(page: Int=1): List<Movie>
}