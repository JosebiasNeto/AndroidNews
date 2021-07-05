package com.example.androidnews

import model.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ArticlesAPI {

    @GET("everything?q=tesla&from=2021-06-05&sortBy=publishedAt&apiKey=b4b9513abbf54d69b6fae62fe10b4351")
    fun getArticles(): Call<ArticlesResponse>

}