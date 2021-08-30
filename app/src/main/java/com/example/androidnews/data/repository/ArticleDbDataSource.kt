package com.example.androidnews.data.repository

import com.example.androidnews.data.db.ArticlesDao
import com.example.androidnews.data.db.ConvertersEntities
import com.example.androidnews.data.model.ArticlesResponse

class ArticleDbDataSource(
    private val articlesDao: ArticlesDao
) : ArticleRepository{
    override suspend fun insert(articlesResponse: ArticlesResponse) {
        val articlesResponseEntity = articlesResponse.toArticlesResponseEntity()
        articlesDao.insert(articlesResponseEntity)
    }

    override suspend fun getArticles(): ArticlesResponse {
        return ConvertersEntities().toArticlesResponse(articlesDao.getArticles())
    }

    override suspend fun deleteArticles() {
        articlesDao.deleteArticles()
    }

}
