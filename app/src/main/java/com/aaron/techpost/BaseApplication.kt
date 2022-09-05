package com.aaron.techpost

import android.app.Application
import com.aaron.techpost.data.database.ArticleDatabase

class BaseApplication : Application() {
    val database: ArticleDatabase by lazy { ArticleDatabase.getDatabase(this) }
}