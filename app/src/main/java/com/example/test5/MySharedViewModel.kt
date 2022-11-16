package com.example.test5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test5.data.repository.Repository
import com.example.test5.models.ServerResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MySharedViewModel : ViewModel() {

    private val _responseFromNetwork: MutableLiveData<Response<ServerResponse>> = MutableLiveData()
    val responseFromNetwork: LiveData<Response<ServerResponse>> = _responseFromNetwork
    val repo = Repository()

    fun getData(){
        viewModelScope.launch {
            _responseFromNetwork.value = repo.getData()
        }
        Thread.sleep(1000)
    }

}