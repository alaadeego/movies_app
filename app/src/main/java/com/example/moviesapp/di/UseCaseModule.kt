package com.example.moviesapp.di

import com.example.moviesapp.data.api.MovieApiService
import com.example.moviesapp.domain.NowPlayingMoviesUseCase
import com.example.moviesapp.domain.NowPlayingMoviesUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideNowPlayingUseCase(movieApiService: MovieApiService): NowPlayingMoviesUseCase =
        NowPlayingMoviesUseCaseImpl(movieApiService)
}