package com.example.androidnews.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ArticlesResponseEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticlesDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

    companion object{
        @Volatile
        private var INSTANCE: ArticlesDatabase? = null

        fun getDatabase(context: Context): ArticlesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticlesDatabase::class.java,
                    "articles_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}