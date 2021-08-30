package com.example.androidnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidnews.data.api.APIHelper
import com.example.androidnews.data.db.ArticlesDao
import com.example.androidnews.data.db.ArticlesDatabase
import com.example.androidnews.data.repository.ArticleDbDataSource
import com.example.androidnews.data.repository.ArticleRepository
import com.example.androidnews.data.repository.MainRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val apiHelper: APIHelper,
    private val articlesDao: ArticleRepository
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(apiHelper), articlesDao) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}