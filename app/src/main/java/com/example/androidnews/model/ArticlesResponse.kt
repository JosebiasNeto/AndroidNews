package com.example.androidnews.model

import com.google.gson.annotations.SerializedName

class ArticlesResponse(
    @SerializedName("status")
    var status: String = "",
    @SerializedName("totalResults")
    var totalResults: String = "",
    @SerializedName("articles")
    var articles: List<Articles>,
    )

class ArticlesResponseBuilder{

    var status: String = ""
    var totalResults: String = ""
    lateinit var articles: List<Articles>

    fun build(): ArticlesResponse = ArticlesResponse(
        status,
        totalResults,
        articles,
    )

}

fun articlesResponse(block: ArticlesBuilder.() -> Unit): Articles = ArticlesBuilder().apply(block).build()