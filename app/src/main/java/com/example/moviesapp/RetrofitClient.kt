package com.example.moviesapp

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(private val baseURL: String
                                         , private val httpClient: OkHttpClient.Builder
                                         , private val builder: Retrofit.Builder) {

    fun getInstance(): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        builder.baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))

        builder.client(httpClient.build())
        return builder.build()
    }


}