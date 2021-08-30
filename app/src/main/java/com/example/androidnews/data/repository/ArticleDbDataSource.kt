package com.example.androidnews.data.repository

import com.example.androidnews.data.db.ArticlesDao
import com.example.androidnews.data.model.ArticlesResponse

class ArticleDbDataSource(
    private val articlesDao: ArticlesDao
) : ArticleRepository{
    override suspend fun insert(articlesResponse: ArticlesResponse) {
        val articlesResponseEntity = articlesResponse.toArticlesResponseEntity()
        articlesDao.insert(articlesResponseEntity)
    }

    override suspend fun getArticles(): ArticlesResponse {
        return articlesDao.getArticles().toArticlesResponse()
    }

    override suspend fun deleteArticles() {
        articlesDao.deleteArticles()
    }

}
