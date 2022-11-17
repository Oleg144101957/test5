package com.example.test5.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.test5.R
import com.example.test5.data.api.NetworkData
import com.example.test5.data.repository.Repository
import com.example.test5.databinding.FragmentFirstBinding
import kotlinx.coroutines.*


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)


        val networkData = NetworkData(requireActivity())
        val repo = Repository()

        val visit = networkData.getData("visitTime")
        if (visit == "NO_DATA"){
            networkData.saveToSharedPref("visitTime", "FirstTime")
        } else {
            networkData.saveToSharedPref("visitTime", "OurClient")
        }

        uiScope.launch {
            withContext(Dispatchers.IO){
                networkData.saveDataFromNetwork(repo)
                withContext(Dispatchers.Main){
                    findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
                }
            }
        }

        return binding.root
    }



}