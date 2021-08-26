package com.example.androidnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androidnews.data.repository.MainRepository
import com.example.androidnews.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception


class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    fun getArticles() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getArticles()))
        } catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}