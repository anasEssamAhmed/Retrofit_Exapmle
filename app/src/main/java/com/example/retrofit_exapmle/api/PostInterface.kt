package com.example.retrofit_exapmle.api

import com.example.retrofit_exapmle.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface PostInterface {

    @GET("posts1/2")
    suspend fun getPost() : Response<Post>

    // use Path
    @GET("posts/{postNumber}")
    suspend fun getPost2(@Path("postNumber") id : Int) : Response<Post>

    // use query
    @GET("posts")
    suspend fun getCustomPost(@Query("userId") userId : Int) : Response<List<Post>>

    // user query map
    @GET("posts")
    suspend fun getCustomPost2(@Query("userId") userId: Int , @QueryMap option : Map<String , String>) : Response<List<Post>>

}