package com.aaron.techpost.data.domain

/**
 * Article data class used in the UI layer of the application.
 */
data class Article(
    val author: String,
    val title: String,
    val sourceName: String,
    val description: String,
    val url: String,
    val image: String,
    val publishedAt: String
)
