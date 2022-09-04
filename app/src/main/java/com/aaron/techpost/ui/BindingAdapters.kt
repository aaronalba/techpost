package com.aaron.techpost.ui

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.aaron.techpost.data.DataStatus
import com.aaron.techpost.util.formatDate
import com.aaron.techpost.util.formatElapsedTime
import com.aaron.techpost.util.processTitle
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
fun bindStatus(progressBar: ProgressBar, status: DataStatus) {
    when(status) {
        DataStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        DataStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
        DataStatus.ERROR -> {
            progressBar.visibility = View.GONE
        }
    }
}

/**
 * Binding adapter for showing the elapsed time since the article was published.
 */
@BindingAdapter("elapsedTime")
fun bindElapsedTime(textView: TextView, utc: String) {
    val elapsedTime = formatElapsedTime(utc)    // get the string representation of the elapsed time
    textView.text = elapsedTime
}

/**
 * Binding adapter for showing the article title.
 */
@BindingAdapter("articleTitle")
fun bindArticleTitle(textView: TextView, title: String) {
    textView.text = processTitle(title)
}