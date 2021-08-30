package com.example.androidnews.data.db

import androidx.room.*

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articlesResponseEntity: ArticlesResponseEntity)

    @Query("SELECT * FROM articles_response")
    fun getArticles(): ArticlesResponseEntity

    @Query("DELETE FROM articles_response")
    fun deleteArticles()

}