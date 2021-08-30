package com.example.androidnews.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun arrayListToJson(value: ArticlesResponseEntity) = Gson().toJson(value)

    @TypeConverter
    fun jsonToArrayList(value: String) = Gson().fromJson(value, ArticlesResponseEntity::class.java)

}