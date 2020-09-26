package com.example.moviesapp.di

import com.example.moviesapp.presentation.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesFragmentBuilder {

    @ContributesAndroidInjector(modules = [UseCaseModule::class])
    internal abstract fun provideNoteListFragment(): MoviesFragment
}