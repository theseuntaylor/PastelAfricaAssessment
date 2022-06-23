package com.theseuntaylor.pastelafricaassessment.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.theseuntaylor.pastelafricaassessment.data.local.NewsDatabase
import com.theseuntaylor.pastelafricaassessment.data.model.Article
import com.theseuntaylor.pastelafricaassessment.data.model.NewsResponse
import com.theseuntaylor.pastelafricaassessment.data.network.ServiceBuilder
import kotlinx.coroutines.flow.Flow

// decided against using Dependency Injection (Hilt) to keep it as simple and light as possible
class NewsRepository(application: Application) {

    // remote
    private val newsDao by lazy {
        NewsDatabase.getDatabase(application).newsDao()
    }

    private val service by lazy {
        ServiceBuilder.service
    }

    suspend fun getNewsList(country: String): NewsResponse {
        return service.getTopHeadlines(country)
    }

    // database...
    suspend fun getNewsOnce() = newsDao.getNewsOnce()

    suspend fun addNews(newsList: List<Article>) {
        newsDao.addNews(newsList = newsList)
    }

    suspend fun deleteNews() {
        newsDao.deleteNews()
    }
}