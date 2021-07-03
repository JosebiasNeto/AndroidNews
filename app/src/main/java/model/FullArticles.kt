package model

import com.example.androidnews.R


data class FullArticles(

    var name: String = "",
    var author: String = "",
    var title: String = "",
    var description: String = "",
    var image: Int = 0,
    var date: String = ""

    )




class FullArticlesBuilder{

    var nameFullArticle: String = ""
    var authorFullArticle: String = ""
    var titleFullArticle: String = ""
    var descriptionFullArticle: String = ""
    var imageFullArticle: Int = 0
    var dateFullArticle: String = ""

    fun build(): FullArticles = FullArticles(
        nameFullArticle,
        authorFullArticle,
        titleFullArticle,
        descriptionFullArticle,
        imageFullArticle,
        dateFullArticle,

        )

}



fun full_articles(block: FullArticlesBuilder.() -> Unit): FullArticles = FullArticlesBuilder().apply(block).build()

fun addFullArticles(): MutableList<FullArticles> = mutableListOf(

    full_articles {
        nameFullArticle = "Name1"
        authorFullArticle = "Author1"
        titleFullArticle = "Title1"
        descriptionFullArticle = "Description1"
        imageFullArticle = R.drawable.image1
        dateFullArticle = "Date1"

    },
    full_articles {
        nameFullArticle = "Name2"
        authorFullArticle = "Author2"
        titleFullArticle = "Title2"
        descriptionFullArticle = "Description2"
        imageFullArticle = R.drawable.image2
        dateFullArticle = "Data2"
    },
    full_articles {

    },
    full_articles {

    },
    full_articles {

    },
    full_articles {

    },
    full_articles {

    },
    )