package com.example.test5.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.test5.MySharedViewModel
import com.example.test5.MyWebViewClient
import com.example.test5.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var webView: WebView
    private val sharedPreferences by lazy { requireActivity().applicationContext.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE) }
    private val vm: MySharedViewModel by activityViewModels()
    private var urlToGo = "NO_DATA"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        val visit = sharedPreferences.getString("visitTime", "noData")

        urlToGo = if (visit == "firstTime"){
            vm.responseFromNetwork.value?.body()?.home.toString()
        }else{
            vm.responseFromNetwork.value?.body()?.link.toString()
        }

        webView = binding.webView
        webView.webViewClient = MyWebViewClient()
        webView.settings.javaScriptEnabled
        webView.loadUrl(urlToGo)


        return binding.root
    }

}