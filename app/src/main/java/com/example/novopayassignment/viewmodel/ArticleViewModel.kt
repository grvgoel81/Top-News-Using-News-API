package com.example.novopayassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.novopayassignment.BaseApplication
import com.example.novopayassignment.model.ArticleResponse
import com.example.novopayassignment.network.repository.NewsRepository
import javax.inject.Inject

class ArticleViewModel: ViewModel() {

    var articlesLiveData: MutableLiveData<ArticleResponse> = MutableLiveData()
    @Inject
    lateinit var mRepository: NewsRepository

    init {
        BaseApplication.app.mApiComponent.inject(this)
    }

    fun getArticlesList(source: String) : LiveData<ArticleResponse> {
        articlesLiveData = this.mRepository.getArticlesBySources(source)
        return  articlesLiveData
    }
}