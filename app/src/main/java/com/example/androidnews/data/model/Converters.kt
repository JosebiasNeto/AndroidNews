package com.example.androidnews.data.model

object Converters {

    fun toArticle(articleEntity: ArticleEntity): Article {
        return Article(
            source = articleEntity.source,
            author = articleEntity.author,
            title = articleEntity.title,
            description = articleEntity.description,
            image = articleEntity.image,
            data = articleEntity.data,
            id = articleEntity.id
        )
    }

    fun toArticleEntity(article: Article): ArticleEntity {
        return ArticleEntity(
            source = article.source,
            author = article.author,
            title = article.title,
            description = article.description,
            image = article.image,
            data = article.data,
            id = article.id
        )
    }

}