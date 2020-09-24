package com.example.moviesapp.di

import com.example.moviesapp.RetrofitClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("BASE_URL")
    fun provideBaseUrl() = ""

    @Provides
    @Singleton
    fun provideHttpClientBuilder() = OkHttpClient().newBuilder()

    @Provides
    @Singleton
    fun provideRetrofitBuilder() = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideRetrofitClient(@Named("BASE_URL") baseURL :String,httpClientBuilder: OkHttpClient.Builder,retrofitBuilder:Retrofit.Builder) :Retrofit
    {
        return RetrofitClient(baseURL,httpClientBuilder,retrofitBuilder).getInstance()
    }
}