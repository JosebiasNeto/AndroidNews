package model

import java.io.Serializable

class Articles(

    var name: String = "",
    var author: String = "",
    var title: String = "",
    var description: String = "",
    var image: Int = 0,
    var data: String = ""
    )




class ArticlesBuilder{

    var nameArticle: String = ""
    var authorArticle: String = ""
    var titleArticle: String = ""
    var description: String = ""
    var image: Int = 0
    var date: String = ""

    fun build(): Articles = Articles(
        nameArticle,
        authorArticle,
        titleArticle,
        description,
        image,
        date,

    )

}



fun articles(block: ArticlesBuilder.() -> Unit): Articles = ArticlesBuilder().apply(block).build()

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





)