package com.example.novopayassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.novopayassignment.BaseApplication
import com.example.novopayassignment.model.SourceResponse
import com.example.novopayassignment.network.repository.NewsRepository
import javax.inject.Inject

class NewsViewModel : ViewModel() {

    var newsLiveData: MutableLiveData<SourceResponse> = MutableLiveData()
    @Inject
    lateinit var mRepository: NewsRepository

    init {
        BaseApplication.app.mApiComponent.inject(this)
        mRepository.getSourcesList()
    }

    fun getNewsSourceList(): LiveData<SourceResponse> {
        newsLiveData = mRepository.sourceData
        return newsLiveData
    }
}