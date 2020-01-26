package com.lgdev.kotlindemo.retrofit

import com.lgdev.kotlindemo.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface IMyApi {
    @get:GET("posts")
    val posts:Observable<List<Post>>
}