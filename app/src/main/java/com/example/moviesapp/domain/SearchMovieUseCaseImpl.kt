package com.example.moviesapp.domain

import com.example.moviesapp.data.api.MovieApiService
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.di.API_KEY

class SearchMovieUseCaseImpl(private val movieApiService: MovieApiService) : SearchMovieUseCase {
    override suspend fun search(page: Int, query: String): List<Movie> {
        return movieApiService.searchMovies(API_KEY, page, query).results
    }
}