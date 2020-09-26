package com.example.moviesapp.di

import android.app.Application
import com.example.moviesapp.MoviesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivityBuilder::class]
)
interface AppComponent {

    fun inject(app: MoviesApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}