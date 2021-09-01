package com.example.androidnews.data.repository

import com.example.androidnews.data.model.Article
import com.example.androidnews.utils.CheckNetworkConnection

class MainRepository(
    private val articlesAPI: ArticlesAPIDataSource,
    private val articlesDb: ArticlesDbDataSource,
    private var networkConnection: CheckNetworkConnection
) {

    suspend fun getArticles(): List<Article> {

        var isOnline: Boolean = networkConnection.updateConnection()
        if (isOnline == true){
            articlesDb.deleteArticles()
            articlesAPI.getArticles().articles.forEach { article ->
                articlesDb.insert(article)
            }
        }
        return articlesDb.getArticles()
    }
}