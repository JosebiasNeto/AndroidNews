package com.example.androidnews.data.repository

import com.example.androidnews.data.model.Article

interface ArticlesDbRepository {

    suspend fun insert(article: Article)
    suspend fun getArticles(): List<Article>
    suspend fun deleteArticles()

}