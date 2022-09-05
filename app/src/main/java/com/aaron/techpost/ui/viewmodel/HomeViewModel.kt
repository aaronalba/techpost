package com.aaron.techpost.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * Contains the UI state for the HomeFragment.
 */
class HomeViewModel : ViewModel() {
    /**
     * The current date.
     */
    private val _date = MutableLiveData<Date>()
    val date: LiveData<Date> = _date

    init {
        updateDate()
    }

    /**
     * Sets the current date to the date property
     */
    private fun updateDate() {
        _date.value = Date()
    }
}