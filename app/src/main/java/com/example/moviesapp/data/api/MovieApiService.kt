package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {


    @GET("3/movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): MoviesResponse


    @GET("3/search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("language") language: String = "en-US"
    ): MoviesResponse

}