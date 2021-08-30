package com.example.androidnews.data.db

import androidx.room.*
import com.example.androidnews.data.model.ArticleEntity

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articleEntity: ArticleEntity)

    @Query("SELECT * FROM articles")
    fun getArticles(): List<ArticleEntity>

    @Query("DELETE FROM articles")
    fun deleteArticles()

}