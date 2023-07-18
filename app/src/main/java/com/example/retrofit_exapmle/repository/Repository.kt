package com.example.retrofit_exapmle.repository

import com.example.retrofit_exapmle.api.RetrofitInstance
import com.example.retrofit_exapmle.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost() : Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}