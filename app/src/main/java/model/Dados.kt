package model

data class Articles(

    var name: String = "",
    var author: String = "",
    var title: String = ""

)



class ArticlesBuilder{

    var nameArticle: String = ""
    var authorArticle: String = ""
    var titleArticle: String = ""

    fun build(): Articles = Articles(nameArticle, authorArticle, titleArticle)

}

fun articles(block: ArticlesBuilder.() -> Unit): Articles = ArticlesBuilder().apply(block).build()

fun addArticles(): MutableList<Articles> = mutableListOf(

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
    articles {

    },
    articles {

    },





)