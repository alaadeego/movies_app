package com.example.moviesapp.data.api

import com.example.moviesapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(
    private val baseURL: String
    , private val httpClient: OkHttpClient.Builder
    , private val httpLoggingInterceptor: HttpLoggingInterceptor
    , private val builder: Retrofit.Builder
) {

    fun getInstance(): Retrofit {

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(httpLoggingInterceptor)

        }

        val gson = GsonBuilder()
            .setLenient()
            .create()

        builder.baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))

        builder.client(httpClient.build())
        return builder.build()
    }


}