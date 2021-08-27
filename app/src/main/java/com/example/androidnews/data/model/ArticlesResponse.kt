package com.example.androidnews.data.model

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("status")
    var status: String = "",
    @SerializedName("totalResults")
    var totalResults: String = "",
    @SerializedName("articles")
    var articles: ArrayList<Article>
)
