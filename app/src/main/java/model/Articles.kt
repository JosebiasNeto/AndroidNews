package model

import com.google.gson.annotations.SerializedName

class Articles(
    @SerializedName("source")
    var source: Source,
    @SerializedName("author")
    var author: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("urlToImage")
    var image: String = "",
    @SerializedName("publishedAt")
    var data: String = ""
    )

class ArticlesBuilder{

    lateinit var sourceArticle: Source
   // var nameArticle: String = ""
    var authorArticle: String = ""
    var titleArticle: String = ""
    var description: String = ""
    var image: String = ""
    var date: String = ""

    fun build(): Articles = Articles(
    //    nameArticle,
        sourceArticle,
        authorArticle,
        titleArticle,
        description,
        image,
        date,

    )

}

fun articles(block: ArticlesBuilder.() -> Unit): Articles = ArticlesBuilder().apply(block).build()

/*
fun getArticles(): MutableList<Articles> = mutableListOf(

    articles {
        nameArticle = "Name1"
        authorArticle = "Author1"
        titleArticle = "Title1"
    },
    articles {
        nameArticle = "Name2"
        authorArticle = "Author2"
        titleArticle = "Title2"
    },
    articles {

    },
    articles {

    },
    articles {

    },
    articles {

    },
    articles {

    },
) */