package com.example.moviesapp.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

data class Dates(
    val maximum: String,
    val minimum: String
)

