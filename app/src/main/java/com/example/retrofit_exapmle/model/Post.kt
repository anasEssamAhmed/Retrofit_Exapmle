package com.example.retrofit_exapmle.model

data class Post(
    // you can use @SerializedName("userId") without use same name in variable
    // @SerializedName("userId")
    val userId : Int = 0,
    val id:Int = 0,
    val title:String = "",
    val body:String = ""
)
