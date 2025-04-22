package com.example.interviewbuddy.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {

    var client = OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()


    var retrofit = Retrofit.Builder()
        .baseUrl("https://api.mistral.ai/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var apiService = retrofit.create(ApiService::class.java)
}

