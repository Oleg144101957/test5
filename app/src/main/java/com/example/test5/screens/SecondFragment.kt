package com.example.test5.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.test5.MyWebViewClient
import com.example.test5.data.api.NetworkData
import com.example.test5.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var webView: WebView
    private var urlToGo = "NO_DATA"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        val networkData = NetworkData(requireActivity())
        val visit = networkData.getData("visitTime")

        if (visit == "FirstTime"){
            urlToGo = networkData.getData("link_pref")
        }else{
            urlToGo = networkData.getData("home_pref")
        }

        webView = binding.webView
        webView.webViewClient = MyWebViewClient()
        webView.settings.javaScriptEnabled
        webView.loadUrl(urlToGo)


        return binding.root
    }

}