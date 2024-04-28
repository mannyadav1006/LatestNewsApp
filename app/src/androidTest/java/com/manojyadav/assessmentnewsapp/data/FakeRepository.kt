package com.manojyadav.assessmentnewsapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.manojyadav.assessmentnewsapp.data.model.NewsArticle
import com.manojyadav.assessmentnewsapp.data.model.NewsResponse
import com.manojyadav.assessmentnewsapp.network.repository.INewsRepository
import com.manojyadav.assessmentnewsapp.state.NetworkState

class FakeRepository : INewsRepository {

    private val observableNewsArticle = MutableLiveData<List<NewsArticle>>()

    override suspend fun getNews(
        countryCode: String,
        pageNumber: Int
    ): NetworkState<NewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun searchNews(
        searchQuery: String,
        pageNumber: Int
    ): NetworkState<NewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(news: NewsArticle): Long {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): LiveData<List<NewsArticle>> {
        return FakeDataUtil.getFakeNewsArticleLiveData()
    }

    override suspend fun deleteNews(news: NewsArticle) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllNews() {
        TODO("Not yet implemented")
    }
}