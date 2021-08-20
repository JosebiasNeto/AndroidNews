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
    var authorArticle: String = ""
    var titleArticle: String = ""
    var description: String = ""
    var image: String = ""
    var date: String = ""

    fun build(): Articles = Articles(
        sourceArticle,
        authorArticle,
        titleArticle,
        description,
        image,
        date,

    )

}

fun articles(block: ArticlesBuilder.() -> Unit): Articles = ArticlesBuilder().apply(block).build()

