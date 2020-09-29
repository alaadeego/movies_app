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

class NowPlayingMoviePagedListRepository(
    private val apiService: MovieApiService,
    private val coroutineScope: CoroutineScope
) {
    lateinit var nowPlayedMoviePagedList: LiveData<PagedList<Movie>>
    lateinit var nowPlayingMovieDataSourceFactory: NowPlayingMovieDataSourceFactory

    fun fetchNowPlayedMoviePagedList(): LiveData<PagedList<Movie>> {
        nowPlayingMovieDataSourceFactory =
            NowPlayingMovieDataSourceFactory(apiService, coroutineScope)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(PAGE_SIZE)
            .build()
        nowPlayedMoviePagedList =
            LivePagedListBuilder(nowPlayingMovieDataSourceFactory, config).build()
        return nowPlayedMoviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<NowPlayingMovieDataSource, NetworkState>(
            nowPlayingMovieDataSourceFactory.nowPlayingMovieLiveData,
            NowPlayingMovieDataSource::networkState
        )
    }
}