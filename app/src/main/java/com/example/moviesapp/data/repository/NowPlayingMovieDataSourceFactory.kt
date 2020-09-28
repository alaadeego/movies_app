package com.example.moviesapp.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviesapp.data.api.MovieApiService
import com.example.moviesapp.data.model.Movie
import kotlinx.coroutines.CoroutineScope

class NowPlayingMovieDataSourceFactory(
    private val apiService: MovieApiService,
    private val coroutineScope: CoroutineScope
) : DataSource.Factory<Int, Movie>() {

    val nowPlayingMovieLiveData = MutableLiveData<NowPlayingMovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val nowPlayingMovieDataSource = NowPlayingMovieDataSource(apiService, coroutineScope)
        nowPlayingMovieLiveData.postValue(nowPlayingMovieDataSource)
        return nowPlayingMovieDataSource
    }
}