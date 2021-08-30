package com.example.androidnews.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidnews.data.model.Article
import com.example.androidnews.data.model.ArticlesResponse

@Entity(tableName = "articles_response")
data class ArticlesResponseEntity(
    @PrimaryKey val id: Long = 0,
    var status: String,
    var totalResults: String,
    var articles: ArrayList<Article>
)
