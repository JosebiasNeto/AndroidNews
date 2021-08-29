package com.example.androidnews.data.repository

import com.example.androidnews.data.db.ArticlesDao
import com.example.androidnews.data.db.toArticle
import com.example.androidnews.data.db.toArticleEntity
import com.example.androidnews.data.model.Article
import com.example.androidnews.ui.registration.RegistrationViewParams

class ArticleDbDataSource(
    private val articlesDao: ArticlesDao
) : ArticleRepository{
    override fun createArticle(registrationViewParams: RegistrationViewParams) {
        val articleEntity = registrationViewParams.toArticleEntity()
        articlesDao.save(articleEntity)
    }

    override fun getArticles(): Article {
        return articlesDao.getArticles().toArticle()
    }
}