package com.manojyadav.assessmentnewsapp.network.repository

import androidx.lifecycle.LiveData
import com.manojyadav.assessmentnewsapp.data.model.NewsArticle
import com.manojyadav.assessmentnewsapp.data.model.NewsResponse
import com.manojyadav.assessmentnewsapp.state.NetworkState

interface INewsRepository {
    suspend fun getNews(countryCode: String, pageNumber: Int): NetworkState<NewsResponse>

    suspend fun searchNews(searchQuery: String, pageNumber: Int): NetworkState<NewsResponse>

    suspend fun saveNews(news: NewsArticle): Long

    fun getSavedNews(): LiveData<List<NewsArticle>>

    suspend fun deleteNews(news: NewsArticle)

    suspend fun deleteAllNews()
}
