package com.example.androidnews.data.repository

import com.example.androidnews.data.api.APIHelper

class MainRepository(private val apiHelper: APIHelper) {
    suspend fun getArticles() = apiHelper.getArticles()
}