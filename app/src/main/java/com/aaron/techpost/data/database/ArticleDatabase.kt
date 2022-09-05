package com.aaron.techpost.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * The database that will cache the articles from the network.
 */
@Database(entities = [DatabaseArticle::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val articleDao: ArticleDao

    companion object {
        var INSTANCE: ArticleDatabase? = null

        fun getDatabase(context: Context): ArticleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ArticleDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}