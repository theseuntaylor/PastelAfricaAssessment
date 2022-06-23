package com.theseuntaylor.pastelafricaassessment.data.network

import com.theseuntaylor.pastelafricaassessment.ui.utils.Const
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {

    private val interceptor = Interceptor { chain ->

        val request = chain.request()
        val requestBuilder = request.newBuilder()
        val url = request.url

        val builder = url.newBuilder()

        requestBuilder.url(builder.build())
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
        chain.proceed(requestBuilder.build())

    }

    private val client: OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build()

    var service: NewsService = retrofit.create(NewsService::class.java)

}