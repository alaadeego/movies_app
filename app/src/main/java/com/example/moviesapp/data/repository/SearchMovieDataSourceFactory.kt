package com.example.moviesapp.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviesapp.data.api.MovieApiService
import com.example.moviesapp.data.model.Movie
import kotlinx.coroutines.CoroutineScope

class SearchMovieDataSourceFactory(
    private val query: String,
    private val apiService: MovieApiService,
    private val coroutineScope: CoroutineScope
) : DataSource.Factory<Int, Movie>() {

    val searchMovieLiveData = MutableLiveData<SearchMovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val searchMovieDataSource = SearchMovieDataSource(query, apiService, coroutineScope)
        searchMovieLiveData.postValue(searchMovieDataSource)
        return searchMovieDataSource
    }
}