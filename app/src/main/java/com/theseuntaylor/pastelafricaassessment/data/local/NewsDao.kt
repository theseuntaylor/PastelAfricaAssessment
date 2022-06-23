package com.theseuntaylor.pastelafricaassessment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.theseuntaylor.pastelafricaassessment.data.model.Article
import kotlinx.coroutines.flow.Flow

object DatabaseConstants {
    const val DATABASE_VERSION = 1
}

@Dao
interface NewsDao {

    @Query("SELECT * from article_table")
    fun getNews(): LiveData<List<Article>>

    @Query("SELECT * from article_table")
    suspend fun getNewsOnce(): List<Article>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNews(newsList: List<Article>)

    @Query("DELETE FROM article_table")
    suspend fun deleteNews()

}