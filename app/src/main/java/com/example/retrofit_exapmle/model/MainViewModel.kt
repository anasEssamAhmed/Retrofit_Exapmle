package com.example.retrofit_exapmle.model

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

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            _myResponse.value = response
        }
    }

}