package com.theseuntaylor.pastelafricaassessment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.theseuntaylor.pastelafricaassessment.data.model.Article
import com.theseuntaylor.pastelafricaassessment.data.model.NewsResponse

@Database(entities = [Article::class], version = DatabaseConstants.DATABASE_VERSION, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {

        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "article_table"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}