package com.aaron.techpost.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aaron.techpost.data.domain.Article

/**
 * Data class for modelling the Article that will be saved on the database.
 */

@Entity(tableName = "articles")
data class DatabaseArticle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "source_name")
    val sourceName: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "published_at")
    val publishedAt: String
) {

    /**
     * Convert this [DatabaseArticle] to [Article] for use in the UI layer.
     */
    fun asDomainModel() = Article(
        id = this.id,
        author = this.author,
        title = this.title,
        sourceName = this.sourceName,
        description = this.description,
        url = this.url,
        image = this.image,
        publishedAt = this.publishedAt
    )
}