package com.example.androidnews.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidnews.data.model.Article
import com.example.androidnews.data.model.Source
import com.example.androidnews.ui.registration.RegistrationViewParams

@Entity(tableName = "articles")
data class ArticleEntity (
    @PrimaryKey val id: Long = 0,
    var source: Source,
    var author: String,
    var title: String,
    var description: String,
    var image: String,
    var data: String
        )

fun RegistrationViewParams.toArticleEntity(): ArticleEntity{
    return with(this){
        ArticleEntity(
            source = this.source,
            author = this.author,
            title = this.title,
            description = this.description,
            image = this.image,
            data = this.data
        )
    }
}

fun ArticleEntity.toArticle(): Article {
    return Article(
        id = this.id.toString(),
        source = this.source,
        author = this.author,
        title = this.title,
        description = this.description,
        image = this.image,
        data = this.data
    )
}