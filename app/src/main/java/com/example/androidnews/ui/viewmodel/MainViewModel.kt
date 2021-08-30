package com.example.androidnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androidnews.data.repository.ArticleDbDataSource
import com.example.androidnews.data.repository.MainRepository
import com.example.androidnews.utils.Resource
import kotlinx.coroutines.Dispatchers


class MainViewModel(
    private val mainRepository: MainRepository,
    private val dbRepository: ArticleDbDataSource
    ): ViewModel() {

    fun getArticlesFromAPI() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getArticles()))
        } catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun getArticlesFromDatabase() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = dbRepository.getArticles()))
        } catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun insertArticlesFromAPItoDatabase() = liveData(Dispatchers.IO) {
        emit(dbRepository.insert(mainRepository.getArticles()))
    }
}