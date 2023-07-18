package com.example.retrofit_exapmle.repository

import com.example.retrofit_exapmle.api.RetrofitInstance
import com.example.retrofit_exapmle.model.Post

class Repository {
    suspend fun getPost() : Post {
        return RetrofitInstance.api.getPost()
    }
}