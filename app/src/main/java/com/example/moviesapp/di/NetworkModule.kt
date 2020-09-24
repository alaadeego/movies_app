package com.example.moviesapp.di

import com.example.moviesapp.data.api.MovieApiService
import com.example.moviesapp.data.api.RetrofitClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


const val API_KEY = "6fe2092090c2ca190cd7f79368bba13a"
private const val BASE_URL = "https://api.themoviedb.org/"

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("BASE_URL")
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideHttpClientBuilder() = OkHttpClient().newBuilder()

    @Provides
    @Singleton
    fun provideRetrofitBuilder() = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        @Named("BASE_URL") baseURL: String,
        httpClientBuilder: OkHttpClient.Builder,
        retrofitBuilder: Retrofit.Builder
    ): Retrofit {
        return RetrofitClient(
            baseURL,
            httpClientBuilder,
            retrofitBuilder
        ).getInstance()
    }

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit) = retrofit.create(MovieApiService::class.java)
}