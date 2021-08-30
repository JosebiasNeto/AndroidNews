package com.example.androidnews.data.repository

import com.example.androidnews.data.model.ArticlesResponse

interface ArticleRepository {

    suspend fun insert(articlesResponse: ArticlesResponse)
    suspend fun getArticles(): ArticlesResponse
    suspend fun deleteArticles()

}