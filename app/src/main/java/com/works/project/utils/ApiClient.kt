package com.works.project.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient(val apiUrl: EApiUrl = EApiUrl.jsonBulut1) {

    val baseUrl1 = "https://jsonbulut.com/api/"
    val baseUrl2 = "https://jsonbulut.com/api/"
    val timeOut = 15000L
    var retrofit: Retrofit? = null

    val okHttpClient = OkHttpClient.Builder().connectTimeout(timeOut, TimeUnit.MILLISECONDS).build()

    fun getClient(): Retrofit {
        val baseUrl = when (apiUrl) {
            EApiUrl.jsonBulut1 -> baseUrl1
            EApiUrl.jsonBulut2 -> baseUrl2
        }
        if (retrofit == null) {
            retrofit = Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit as Retrofit
    }

}