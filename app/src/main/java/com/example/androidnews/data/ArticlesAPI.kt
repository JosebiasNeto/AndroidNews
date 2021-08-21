package com.example.androidnews.data

import com.example.androidnews.model.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ArticlesAPI {

    @GET("everything?q=tesla&from=2021-07-21&sortBy=publishedAt&apiKey=52908155988644249940742a6fa114c1")
    fun getArticles(): Call<ArticlesResponse>

}