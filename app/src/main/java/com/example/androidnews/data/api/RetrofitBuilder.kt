package com.example.androidnews.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitBuilder {

    private const val BASE_URL = "https://newsapi.org/v2/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val articlesAPI : ArticlesAPI = getRetrofit().create(ArticlesAPI::class.java)
}
