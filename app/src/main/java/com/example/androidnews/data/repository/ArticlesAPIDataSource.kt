package com.example.androidnews.data.repository

import com.example.androidnews.data.api.ArticlesAPI

class ArticlesAPIDataSource(private val articlesAPI: ArticlesAPI) {
    suspend fun getArticles() = articlesAPI.getArticles()
}