package com.aaron.techpost.data.network

import com.aaron.techpost.data.database.DatabaseArticle
import com.aaron.techpost.data.domain.Article
import com.squareup.moshi.Json

/**
 * Data class that models the individual news article received from the api.
 */
data class NetworkArticle(
    val source: Source,
    val author: String? = "",
    val title: String = "",
    val description: String = "",
    val url: String? = "",
    @Json(name="urlToImage") val image: String? = "",
    val publishedAt: String = "",
    val content: String? = "",
) {

    /**
     * Convert this [NetworkArticle] into an [Article] to be used in
     * the UI of the application.
     */
    fun asDomainModel() = Article(
        id = hashCode(),
        author = this.author ?: "no author",
        title = this.title,
        sourceName = this.source.name ?: "no source name",
        description = this.description,
        url = this.url ?: "",
        image = this.image ?: "",
        publishedAt = this.publishedAt
    )

    /**
     * Convert this [NetworkArticle] into a [DatabaseArticle] for saving
     * to the local cache.
     */
    fun asDatabaseModel() = DatabaseArticle(
        id = hashCode(),
        author = this.author ?: "no author",
        title = this.title,
        sourceName = this.source.name ?: "no source name",
        description = this.description,
        url = this.url ?: "",
        image = this.image ?: "",
        publishedAt = this.publishedAt
    )
}

/**
 * Models the Source property in the received [NetworkArticle].
 */
data class Source(
    val id: String? = "",
    val name: String? = "",
)