package com.example.moviesapp.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviesapp.data.model.Movie

interface SearchMovieUseCase {
     fun search(query: String): LiveData<PagedList<Movie>>
}