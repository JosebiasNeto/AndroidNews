package com.example.androidnews.data.model

import com.example.androidnews.data.db.ArticlesResponseEntity
import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("status")
    var status: String = "",
    @SerializedName("totalResults")
    var totalResults: String = "",
    @SerializedName("articles")
    var articles: ArrayList<Article>
) {
    fun toArticlesResponseEntity(): ArticlesResponseEntity {
        return with(this){
            ArticlesResponseEntity(
                status = this.status,
                totalResults = this.totalResults,
                articles = this.articles
            )
        }
    }
}
