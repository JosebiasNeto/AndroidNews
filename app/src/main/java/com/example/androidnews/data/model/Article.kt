package com.example.androidnews.data.model

import com.google.gson.annotations.SerializedName

data class Article(

    @SerializedName("source")
    var source: Source = Source(),
    @SerializedName("author")
    var author: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("urlToImage")
    var image: String = "",
    @SerializedName("publishedAt")
    var data: String = ""
)



