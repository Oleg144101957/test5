package com.example.test5.data.api

import android.content.Context
import com.example.test5.data.repository.Repository

class NetworkData(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)

    suspend fun saveDataFromNetwork(repository: Repository){
        val home = repository.getData().body()?.home
        val link = repository.getData().body()?.link
        sharedPreferences.edit().putString("link_pref", link).apply()
        sharedPreferences.edit().putString("home_pref", home).apply()
    }

    fun getData(key: String): String{
        return sharedPreferences.getString(key, "NO_DATA") ?: "NO_DATA"
    }

    fun saveToSharedPref(key: String, value: String){
        sharedPreferences.edit().putString(key, value).apply()
    }


}