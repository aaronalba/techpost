package com.aaron.techpost.ui.viewmodel

import androidx.lifecycle.*
import com.aaron.techpost.data.DataStatus
import com.aaron.techpost.data.database.ArticleDao
import com.aaron.techpost.data.domain.Article
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
    private val _networkStatus = MutableLiveData<DataStatus>()
    val networkStatus: LiveData<DataStatus> = _networkStatus

    /**
     * Status property of the database operation
     */
    private val _databaseStatus = MutableLiveData<DataStatus>(DataStatus.LOADING)
    val databaseStatus: LiveData<DataStatus> = _databaseStatus


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
            _networkStatus.value = DataStatus.LOADING
            try {
                repository.refreshArticles()
                _networkStatus.value = DataStatus.DONE
            } catch (e: Exception) {
                _networkStatus.value = DataStatus.ERROR
            }
        }
    }

    /**
     * Updates the database status property with the new status.
     */
    fun updateDatabaseStatus(status: DataStatus) {
        _databaseStatus.value = status
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