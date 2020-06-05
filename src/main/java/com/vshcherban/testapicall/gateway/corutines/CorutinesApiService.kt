package com.vshcherban.testapicall.gateway.corutines

import com.vshcherban.testapicall.models.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface CorutinesApiService {

    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @GET("/posts/{id}")
    suspend fun getPost(@Path(value = "id") todoId: Int): Post
}




