package com.example.androidnews.data.model

import com.google.gson.annotations.SerializedName

class Source(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("name")
    var name: String = "",
    )


