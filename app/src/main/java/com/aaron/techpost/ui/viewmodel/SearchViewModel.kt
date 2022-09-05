package com.aaron.techpost.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *  Contains the UI state for SearchFragment.
 */
class SearchViewModel : ViewModel() {
    /**
     * Keeps track if there is a result in a given search.
     */
    private val _hasResult = MutableLiveData(false)
    val hasResult: LiveData<Boolean> = _hasResult


    /**
     * Updates the hasResult property with the given boolean value.
     */
    fun updateHasResult(b: Boolean) {
        _hasResult.value = b
    }
}