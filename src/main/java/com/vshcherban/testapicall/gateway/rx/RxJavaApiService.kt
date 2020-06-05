package com.vshcherban.testapicall.gateway.rx

import com.vshcherban.testapicall.models.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RxJavaApiService {
    @GET("/posts")
    fun getPosts(): Single<List<Post>>

    @GET("/posts/{id}")
    fun getPost(@Path(value = "id") todoId: Int): Single<Post>
}