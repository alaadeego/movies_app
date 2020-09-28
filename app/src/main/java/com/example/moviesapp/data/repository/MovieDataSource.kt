package com.example.moviesapp.data.repository

import com.example.moviesapp.data.api.MovieApiService
import kotlinx.coroutines.CoroutineScope

class MovieDataSource(
    private val apiService: MovieApiService,
    private val coroutineScope: CoroutineScope
) {

}