package com.example.moviesapp.presentation

import com.example.moviesapp.data.model.Movie

interface MovieItemClickListener {
    fun onMovieClicked(movie: Movie)
}