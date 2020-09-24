package com.example.moviesapp.di

import android.app.Application
import com.example.moviesapp.MoviesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class]
)
interface AppComponent {

    fun inject(app:MoviesApplication)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application) :Builder
        fun build() :AppComponent
    }
}