package com.example.retrofit_exapmle.repository

import com.example.retrofit_exapmle.api.RetrofitInstance
import com.example.retrofit_exapmle.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost() : Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(id : Int) : Response<Post> {
        return RetrofitInstance.api.getPost2(id)
    }

    suspend fun getCustomPost(userId : Int) : Response<List<Post>>{
        return RetrofitInstance.api.getCustomPost(userId)
    }

    suspend fun getCustomPost2(userId: Int , option:Map<String ,String>) : Response<List<Post>> {
        return RetrofitInstance.api.getCustomPost2(userId , option)
    }

}