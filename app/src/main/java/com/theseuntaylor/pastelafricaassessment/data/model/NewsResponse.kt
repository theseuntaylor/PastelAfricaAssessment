package com.theseuntaylor.pastelafricaassessment.data.model

data class NewsResponse(
    var status: String = "",
    var totalResults: Int = -100,
    var articles: List<Article> = listOf()
)