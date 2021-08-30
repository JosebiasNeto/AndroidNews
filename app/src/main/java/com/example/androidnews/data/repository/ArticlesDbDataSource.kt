package com.example.androidnews.data.repository

import com.example.androidnews.data.db.ArticlesDao
import com.example.androidnews.data.model.Article
import com.example.androidnews.data.model.Converters

class ArticlesDbDataSource(
    private val articlesDao: ArticlesDao
) : ArticlesDbRepository{
    override suspend fun insert(article: Article) {
        val articleEntity = Converters.toArticleEntity(article)
        articlesDao.insert(articleEntity)
    }

    override suspend fun getArticles(): List<Article> {
        return articlesDao.getArticles().map { articleEntity ->
            Converters.toArticle(articleEntity)
        }
    }

    override suspend fun deleteArticles() {
        articlesDao.deleteArticles()
    }



}
