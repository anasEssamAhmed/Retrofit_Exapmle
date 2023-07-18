package com.example.retrofit_exapmle.api

import com.example.retrofit_exapmle.model.Post
import retrofit2.http.GET

interface PostInterface {
    @GET("posts/2")
    suspend fun getPost() : Post
}