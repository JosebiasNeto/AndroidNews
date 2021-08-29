package com.example.androidnews.data.api

import com.example.androidnews.data.model.ArticlesResponse
import retrofit2.http.GET

interface ArticlesAPI {

    @GET("everything?q=tesla&from=2021-07-28&sortBy=publishedAt&apiKey=52908155988644249940742a6fa114c1")
    suspend fun getArticles(): ArticlesResponse

}


