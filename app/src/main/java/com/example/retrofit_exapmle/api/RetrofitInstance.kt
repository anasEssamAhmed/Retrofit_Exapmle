package com.example.retrofit_exapmle.api

import com.example.retrofit_exapmle.util.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api:PostInterface by lazy {
        retrofit.create(PostInterface::class.java)
    }
}