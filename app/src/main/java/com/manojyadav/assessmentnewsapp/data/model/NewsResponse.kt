package com.manojyadav.assessmentnewsapp.data.model

data class NewsResponse(
    val articles: MutableList<NewsArticle>,
    val status: String,
    val totalResults: Int
)