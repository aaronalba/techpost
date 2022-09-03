package com.aaron.techpost.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.aaron.techpost.util.formatDate
import java.util.*

@BindingAdapter("date")
fun bindFormattedDate(textView: TextView, date: Date) {
    textView.text = formatDate(date = date)
}