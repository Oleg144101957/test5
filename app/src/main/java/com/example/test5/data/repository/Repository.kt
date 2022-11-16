package com.example.test5.data.repository

import com.example.test5.data.api.RetrofitInstance
import com.example.test5.models.ServerResponse
import retrofit2.Response

class Repository {

    suspend fun getData(): Response<ServerResponse>{
        return RetrofitInstance.api.getDataFromNetwork()
    }

}