package com.example.novopayassignment.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(primaryKeys = ["title"])
data class NewsData(
    val title: String,
    val description: String,
    val author: String,
    @SerializedName("urlToImage")
    val url_to_image: String,
    @SerializedName("publishedAt")
    val published_at: String,
    val source: Source,
    val url: String

) {
    data class Source(
        val name: String
    )
}

data class SourceResponse(val status: String, val sources: List<Sources>) : Serializable

data class Sources(
    val id: String, val name: String, val description: String,
    val url: String, val category: String, val language: String, val country: String
) : Serializable

data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Articles>
) : Serializable

data class Articles(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    val content: String
) : Serializable

data class Source(val id: String, val name: String)