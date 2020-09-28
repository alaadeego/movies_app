package com.example.moviesapp.data.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.moviesapp.data.api.MovieApiService
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.di.API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NowPlayingMovieDataSource(
    private val apiService: MovieApiService,
    private val coroutineScope: CoroutineScope
) : PageKeyedDataSource<Int, Movie>() {
    private var page = 1
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        coroutineScope.launch {
            val results = kotlin.runCatching {
                apiService.getNowPlaying(API_KEY, page)
            }
            results.onSuccess {
                callback.onResult(it.results, null, page + 1)
            }
            results.onFailure {
                // handle failure case
                Log.e("MovieDataSource", it.message ?: "unexpected error")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        coroutineScope.launch {
            val results = kotlin.runCatching {
                apiService.getNowPlaying(API_KEY, params.key)
            }
            results.onSuccess {
                if (it.total_pages >= params.key) {
                    callback.onResult(it.results, params.key + 1)
                } else {
                    // in case no more pages
                }
            }
            results.onFailure {
                // handle failure case
                Log.e("MovieDataSource", it.message ?: "unexpected error")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        // nothing to implement
    }

}