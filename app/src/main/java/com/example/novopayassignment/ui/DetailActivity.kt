package com.example.novopayassignment.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.novopayassignment.R
import com.example.novopayassignment.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_detail

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideProgress()
        showData()
        setToolbarText("News Details")
        val url: String = intent.getStringExtra("url");
        binding.wvNewsDetails.settings.javaScriptEnabled = true;
        binding.wvNewsDetails.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        binding.wvNewsDetails.loadUrl(url)

    }

}