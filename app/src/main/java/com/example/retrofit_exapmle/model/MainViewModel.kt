package com.example.retrofit_exapmle.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_exapmle.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _myResponse = MutableStateFlow<Response<Post>>(Response.success(Post()))
    val myResponse = _myResponse.asStateFlow()

    private val _myResponse2 = MutableStateFlow<Response<Post>>(Response.success(Post()))
    val myResponse2 = _myResponse2.asStateFlow()

    private val _id = MutableStateFlow("1")
    val id = _id.asStateFlow()

    private val _myResponse3 = MutableStateFlow<Response<List<Post>>>(Response.success(mutableListOf()))
    val myResponse3 = _myResponse3.asStateFlow()
    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            _myResponse.value = response
        }
    }

    fun getPost2(id: String) {
        viewModelScope.launch {
            if (id.isNotEmpty()) {
                val number = Integer.parseInt(id)
                val response = repository.getPost2(number)
                _myResponse2.value = response
            }
        }
    }

    // change Id
    fun updateId(id: String) {
        _id.value = id
    }

    fun getCustomPost(userId: String) {
        viewModelScope.launch {
            if (userId.isNotEmpty()) {
                val number = Integer.parseInt(userId)
                val response = repository.getCustomPost(number)
                _myResponse3.value = response
            }
        }
    }

    // use query map
    fun getCustomPost2(userId: String , option:Map<String , String>){
        viewModelScope.launch {
            if (userId.isNotEmpty() && option.isNotEmpty()){
                val number = Integer.parseInt(userId)
                val response = repository.getCustomPost2(number , option)
                _myResponse3.value = response
            }
        }
    }

}