package com.example.moviesapp.domain

import com.example.moviesapp.data.model.Movie

interface SearchMovieUseCase {
    suspend fun search(page: Int = 1, query: String): List<Movie>
}