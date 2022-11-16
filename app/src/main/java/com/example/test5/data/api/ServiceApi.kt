package com.example.test5.data.api

import com.example.test5.models.ServerResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {

    @GET("/prod")
    suspend fun getDataFromNetwork(): Response<ServerResponse>

}