package com.aaron.techpost.data.repository

import com.aaron.techpost.data.database.ArticleDao
import com.aaron.techpost.data.domain.Article
import com.aaron.techpost.data.network.ArticleApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Repository for fetching articles from the network and storing them on disk.
 */
class ArticleRepository(val articleDao: ArticleDao) {

    /**
     * Updates the database cache of the articles with the result from the network api.
     */
    suspend fun refreshArticles() {
        withContext(Dispatchers.IO) {
            val networkArticles = ArticleApi.retrofitService.getArticles()
            articleDao.insertAll(networkArticles.map { it.asDatabaseModel() })
        }
    }

    /**
     * List of articles from the database.
     */
    val articles: Flow<List<Article>> = articleDao.getArticles()
        .map { articles -> articles.map { it.asDomainModel() } }
}