package com.example.novopayassignment.di

import com.example.novopayassignment.network.api.NewsApi
import com.example.novopayassignment.network.repository.NewsRepository
import com.example.novopayassignment.viewmodel.ArticleViewModel
import com.example.novopayassignment.viewmodel.NewsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiHelper::class, AppModule::class, DBModule::class])
interface ApiComponent {

    val newsApi: NewsApi

    fun inject(repo: NewsRepository)
    fun inject(newsVM: NewsViewModel)
    fun inject(articleVM: ArticleViewModel)
}