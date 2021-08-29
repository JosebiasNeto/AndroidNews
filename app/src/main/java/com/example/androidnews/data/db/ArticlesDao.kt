package com.example.androidnews.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(article: ArticleEntity)

    @Query("SELECT * FROM articles")
    fun getArticles(): ArticleEntity
}