package com.vshcherban.testapicall.gateway.corutines

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CorutinesApiClient {

        private val retrofit by lazy{
            val logger = HttpLoggingInterceptor()
            val client = OkHttpClient.Builder().addInterceptor(logger)
            logger.level = HttpLoggingInterceptor.Level.BODY

           Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client.build())
                .build()

        }

    val apiService: CorutinesApiService = retrofit.create(CorutinesApiService::class.java)
}