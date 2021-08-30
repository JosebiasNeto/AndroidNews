package com.example.androidnews.data.db

import com.example.androidnews.data.model.ArticlesResponse

class ConvertersEntities {

    fun toArticlesResponse(articlesResponseEntity: ArticlesResponseEntity): ArticlesResponse {
        return ArticlesResponse(
            status = articlesResponseEntity.status,
            totalResults = articlesResponseEntity.totalResults,
            articles = articlesResponseEntity.articles
        )
    }

}