package com.aaron.techpost.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.aaron.techpost.data.database.ArticleDao
import com.aaron.techpost.data.domain.Article
import com.aaron.techpost.data.network.ArticleApiStatus
import com.aaron.techpost.data.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * The shared view model between fragments.
 */
class MainViewModel(articleDao: ArticleDao) : ViewModel() {

    /**
     * Article data source
     */
    private val repository = ArticleRepository(articleDao)

    /**
     * Status property of the network api.
     */
    private val _status = MutableLiveData<ArticleApiStatus>()
    val status: LiveData<ArticleApiStatus> = _status


    /**
     * List of articles.
     */
    val articles: Flow<List<Article>> = repository.articles

    init {
        refreshDataFromRepository()
    }

    /**
     * Makes the repository refresh its article data.
     */
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = ArticleApiStatus.LOADING
            try {
                repository.refreshArticles()
                _status.value = ArticleApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ArticleApiStatus.ERROR
            }
        }
    }
}

/**
 * Class for creating MainViewModel with custom articleDao parameter.
 */
class MainViewModelFactory(val articleDao: ArticleDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(articleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}