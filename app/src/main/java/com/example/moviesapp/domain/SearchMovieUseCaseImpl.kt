package com.example.moviesapp.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.repository.SearchMoviePagedListRepository

class SearchMovieUseCaseImpl(private val repository: SearchMoviePagedListRepository) :
    SearchMovieUseCase {


    override fun search(query: String): LiveData<PagedList<Movie>> {
        return repository.searchMoviePagedList(query)
    }
}