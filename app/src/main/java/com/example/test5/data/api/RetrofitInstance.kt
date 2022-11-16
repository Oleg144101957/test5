package com.example.test5.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val api = Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://efs5i1ube5.execute-api.eu-central-1.amazonaws.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServiceApi::class.java)

}