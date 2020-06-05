package com.vshcherban.testapicall.gateway.rx

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RxJavaApiClient {
    private val retrofit by lazy{
        val logger = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder().addInterceptor(logger)
        logger.level = HttpLoggingInterceptor.Level.BODY

        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("https://jsonplaceholder.typicode.com")
            .client(client.build())
            .build()

    }

    val apiService: RxJavaApiService = retrofit.create(RxJavaApiService::class.java)
}