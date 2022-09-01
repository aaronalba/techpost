package com.aaron.techpost.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class TechpostViewModel : ViewModel() {

    /**
     * Current date property
     */
    private val _date = MutableLiveData<Date>(Date())
    val date: LiveData<Date> = _date


    init {
        updateDate()
    }

    /**
     * Updates the date property
     */
    private fun updateDate() {
        _date.value = Date()
    }
}