package com.example.moviesapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.moviesapp.data.api.MovieApiService
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.NetworkState
import com.example.moviesapp.di.API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SearchMovieDataSource(
    private val query: String,
    private val apiService: MovieApiService,
    private val coroutineScope: CoroutineScope
) : PageKeyedDataSource<Int, Movie>() {
    private var page = 1
    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        networkState.postValue(NetworkState.LOADING)

        coroutineScope.launch {
            val results = kotlin.runCatching {
                apiService.searchMovies(API_KEY, page, query)
            }
            results.onSuccess {
                callback.onResult(it.results, null, page + 1)
                networkState.postValue(NetworkState.LOADED)

            }
            results.onFailure {
                // handle failure case
                networkState.postValue(NetworkState.ERROR)
                Log.e("MovieDataSource", it.message ?: "unexpected error")
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        coroutineScope.launch {
            val results = kotlin.runCatching {
                apiService.searchMovies(API_KEY, page, query)
            }
            results.onSuccess {
                if (it.total_pages >= params.key) {
                    callback.onResult(it.results, params.key + 1)
                    networkState.postValue(NetworkState.LOADED)

                } else {
                    // in case no more pages
                    networkState.postValue(NetworkState.ENDOFLIST)
                }
            }
            results.onFailure {
                // handle failure case
                networkState.postValue(NetworkState.ERROR)
                Log.e("MovieDataSource", it.message ?: "unexpected error")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        // nothing to implement
    }

}