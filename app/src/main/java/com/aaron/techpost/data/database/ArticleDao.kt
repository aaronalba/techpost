package com.aaron.techpost.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The interface that defines the database operations that Room will implement.
 */

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles ORDER BY id ASC")
    fun getArticles(): Flow<List<DatabaseArticle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<DatabaseArticle>)
}