package com.example.androidnews.data.repository

import com.example.androidnews.data.model.Article
import com.example.androidnews.ui.registration.RegistrationViewParams

interface ArticleRepository {

    fun createArticle(registrationViewParams: RegistrationViewParams)

    fun getArticles(): Article

}