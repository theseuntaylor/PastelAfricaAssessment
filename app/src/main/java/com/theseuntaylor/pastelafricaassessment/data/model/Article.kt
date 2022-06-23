package com.theseuntaylor.pastelafricaassessment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class Article(
    var author: String? = "",
    var title: String? = "",
    var description: String? = "",
    @PrimaryKey var url: String = "",
    var urlToImage: String? = "",
    var publishedAt: String? = "",
    var content: String? = ""
)

data class SourceModel(
    var id: String? = "fox-news",
    var name: String? = "Fox News"
)