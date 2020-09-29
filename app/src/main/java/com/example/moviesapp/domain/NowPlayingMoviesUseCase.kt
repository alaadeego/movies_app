package com.example.moviesapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.NetworkState

interface NowPlayingMoviesUseCase {
    fun getMovies(): LiveData<PagedList<Movie>>
    fun getNetworkState():LiveData<NetworkState>
     fun isListEmpty(): Boolean
}