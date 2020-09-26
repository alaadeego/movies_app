package com.example.moviesapp.di

import com.example.moviesapp.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MoviesFragmentBuilder::class])
    abstract fun bindMainActivity(): MainActivity
}