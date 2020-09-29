package com.example.moviesapp.di

import com.example.moviesapp.data.api.MovieApiService
import com.example.moviesapp.data.repository.NowPlayingMoviePagedListRepository
import com.example.moviesapp.domain.NowPlayingMoviesUseCase
import com.example.moviesapp.domain.NowPlayingMoviesUseCaseImpl
import com.example.moviesapp.domain.SearchMovieUseCase
import com.example.moviesapp.domain.SearchMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Module
class UseCaseModule {

    @Provides
    fun provideIOCoroutineScope(): CoroutineScope = CoroutineScope(Job() + Dispatchers.IO)


    @Provides
    fun provideNowPlayingMovieRepository(
        movieApiService: MovieApiService,
        coroutineScope: CoroutineScope
    ) =
        NowPlayingMoviePagedListRepository(movieApiService, coroutineScope)

    @Provides
    fun provideNowPlayingUseCase(repository: NowPlayingMoviePagedListRepository): NowPlayingMoviesUseCase =
        NowPlayingMoviesUseCaseImpl(repository)

    @Provides
    fun provideSearchMoviesUseCase(movieApiService: MovieApiService): SearchMovieUseCase =
        SearchMovieUseCaseImpl(movieApiService)
}