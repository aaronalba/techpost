package com.aaron.techpost.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaron.techpost.data.domain.Article
import com.aaron.techpost.data.network.ArticleApiStatus
import com.aaron.techpost.data.repository.ArticleRepository
import kotlinx.coroutines.launch

/**
 * The shared view model between fragments.
 */
class MainViewModel : ViewModel() {

    /**
     * Status property of the network api.
     */
    private val _status = MutableLiveData<ArticleApiStatus>()
    val status: LiveData<ArticleApiStatus> = _status

    /**
     * Article data source
     */
    private val repository = ArticleRepository()

    // TODO: temporary live data property that holds the list of articles from the data source
    // TODO: convert to property from the repository when the database caching is implemented
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    init {
        updateArticles()
    }

    /**
     * Makes the repository refresh its article data.
     * TODO: currently this makes the repository directly fetch data from the network. update when database is implemented
     */
    private fun updateArticles() {
        viewModelScope.launch {
            _status.value = ArticleApiStatus.LOADING
            try {
                _articles.value = repository.getArticles()
                _status.value = ArticleApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ArticleApiStatus.ERROR
            }
        }
    }
}