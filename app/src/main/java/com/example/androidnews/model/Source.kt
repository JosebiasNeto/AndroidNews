package com.example.androidnews.model

import com.google.gson.annotations.SerializedName

class Source(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("name")
    var name: String = "",

)

class SourceBuilder{

    var idSource: String = ""
    var nameSource: String = ""


    fun build(): Source = Source(
        idSource,
        nameSource,
        )

}

fun source(block: SourceBuilder.() -> Unit): Source = SourceBuilder().apply(block).build()

