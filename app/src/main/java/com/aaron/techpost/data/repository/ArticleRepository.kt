package com.aaron.techpost.data.repository

import com.aaron.techpost.data.domain.Article
import com.aaron.techpost.data.network.ArticleApi

/**
 * Repository for fetching articles from the network and storing them on disk.
 */
class ArticleRepository {

    // TODO: implement database caching
    suspend fun getArticles(): List<Article> {
        return ArticleApi.retrofitService.getArticles().map {
            it.asDomainModel()
        }
    }
}