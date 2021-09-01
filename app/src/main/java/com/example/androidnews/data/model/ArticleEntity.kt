package com.example.androidnews.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.androidnews.data.model.Source

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @Ignore
    var source: Source = Source(),
    var author: String? = "",
    var title: String? = "",
    var description: String? = "",
    var image: String? = "",
    var data: String? = "",
)

