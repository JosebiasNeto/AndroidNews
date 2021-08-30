package com.example.androidnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidnews.data.api.ArticlesAPI
import com.example.androidnews.data.db.ArticlesDatabase
import com.example.androidnews.data.repository.ArticlesAPIDataSource
import com.example.androidnews.data.repository.ArticlesDbDataSource
import com.example.androidnews.data.repository.MainRepository
import com.example.androidnews.utils.CheckNetworkConnection
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val articlesAPIDataSource: ArticlesAPIDataSource,
    private val articlesDbDataSource: ArticlesDbDataSource,
    private val networkConnection: CheckNetworkConnection
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(articlesAPIDataSource,articlesDbDataSource,networkConnection)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}