package com.example.androidnews.data.api

class APIHelper(private val articlesAPI: ArticlesAPI) {

    suspend fun getArticles() = articlesAPI.getArticles()

}