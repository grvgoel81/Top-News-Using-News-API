package com.example.novopayassignment.network.api

import com.example.novopayassignment.model.ArticleResponse
import com.example.novopayassignment.model.NewsData
import com.example.novopayassignment.model.SourceResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList

interface NewsApi {

    @GET("sources")
    fun getSourceList(@Query("language") language: String, @Query("country") country: String, @Query("apiKey") apiKey: String): Observable<SourceResponse>

    @GET("top-headlines")
    fun getArticles(@Query("sources") sources: String, @Query("apiKey") apiKey: String): Observable<ArticleResponse>

}