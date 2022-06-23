package com.theseuntaylor.pastelafricaassessment.data.network

import com.theseuntaylor.pastelafricaassessment.data.model.NewsResponse
import com.theseuntaylor.pastelafricaassessment.ui.utils.Const
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET(Const.TOP_HEADLINES)
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = Const.API_KEY,
    ): NewsResponse

}