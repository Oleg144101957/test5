package com.example.test5.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.test5.MySharedViewModel
import com.example.test5.R
import com.example.test5.databinding.FragmentFirstBinding
import kotlinx.coroutines.*


class FirstFragment : Fragment() {


    private lateinit var binding: FragmentFirstBinding
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val vm: MySharedViewModel by activityViewModels()
    private val sharedPreferences by lazy { requireActivity().applicationContext.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)

        val visit = sharedPreferences.getString("visitTime", "NO_DATA")
        if (visit == "NO_DATA"){
            sharedPreferences.edit().putString("visitTime", "FirstTime").apply()
        } else {
            sharedPreferences.edit().putString("visitTime", "NotFirst").apply()
        }

        val fragment2 = SecondFragment()

        uiScope.launch {
            withContext(Dispatchers.IO){
                vm.getData()
                withContext(Dispatchers.Main){
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.nav_host, fragment2)
                        .commit()
                }
            }
        }

        return binding.root
    }

}