package com.example.moviesapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviesapp.data.api.MovieApiService
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.NetworkState
import kotlinx.coroutines.CoroutineScope

private const val PAGE_SIZE = 20

class SearchMoviePagedListRepository(
    private val apiService: MovieApiService,
    private val coroutineScope: CoroutineScope
) {
    lateinit var searchMoviePagedList: LiveData<PagedList<Movie>>
    lateinit var factory: SearchMovieDataSourceFactory

    fun searchMoviePagedList(query: String): LiveData<PagedList<Movie>> {
        factory =
            SearchMovieDataSourceFactory(query, apiService, coroutineScope)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()
        searchMoviePagedList =
            LivePagedListBuilder(factory, config).build()
        return searchMoviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<SearchMovieDataSource, NetworkState>(
            factory.searchMovieLiveData,
            SearchMovieDataSource::networkState
        )
    }
}