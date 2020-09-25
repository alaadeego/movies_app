package com.example.moviesapp.domain

import com.example.moviesapp.data.api.MovieApiService
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.di.API_KEY

class NowPlayingMoviesUseCaseImpl constructor(private val movieApiService: MovieApiService) :
    NowPlayingMoviesUseCase {
    override suspend fun getMovies(page: Int): List<Movie> {
        return movieApiService.getNowPlaying(apiKey = API_KEY, page = page).results
    }
}