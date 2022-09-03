package com.aaron.techpost.ui

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.aaron.techpost.data.network.ArticleApiStatus
import com.aaron.techpost.util.formatDate
import java.util.*

/**
 * Binding adapter used to set the text property of the Date TextView in HomeFragment
 * to show the current date.
 */
@BindingAdapter("date")
fun bindFormattedDate(textView: TextView, date: Date) {
    textView.text = formatDate(date = date)
}

/**
 * Binding adapter used to display images in each of the items in Article list using Coil.
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    imageView.load(url)
}

/**
 * Binding adapter used to show the network status using the progress bar view.
 */
@BindingAdapter("apiStatus")
fun bindStatus(progressBar: ProgressBar, status: ArticleApiStatus) {
    when(status) {
        ArticleApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        ArticleApiStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
        ArticleApiStatus.ERROR -> {
            progressBar.visibility = View.GONE
        }
    }
}