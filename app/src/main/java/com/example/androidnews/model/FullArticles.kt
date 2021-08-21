package com.example.androidnews.model


data class FullArticles(

    var id: Int = 0,
    var name: String = "",
    var author: String = "",
    var title: String = "",
    var description: String = "",
    var image: Int = 0,
    var date: String = ""

    )

class FullArticlesBuilder{

    var idFullArticle: Int = 0
    var nameFullArticle: String = ""
    var authorFullArticle: String = ""
    var titleFullArticle: String = ""
    var descriptionFullArticle: String = ""
    var imageFullArticle: Int = 0
    var dateFullArticle: String = ""

    fun build(): FullArticles = FullArticles(
        idFullArticle,
        nameFullArticle,
        authorFullArticle,
        titleFullArticle,
        descriptionFullArticle,
        imageFullArticle,
        dateFullArticle,

        )

}



fun full_articles(block: FullArticlesBuilder.() -> Unit): FullArticles = FullArticlesBuilder().apply(block).build()

