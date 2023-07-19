package com.example.retrofit_exapmle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_exapmle.model.MainViewModel
import com.example.retrofit_exapmle.repository.Repository
import com.example.retrofit_exapmle.ui.theme.Retrofit_ExapmleTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = Repository()
        val mainViewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        // viewModel.getPost()
        setContent {
            Retrofit_ExapmleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowPost(viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowPost(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    // val response by viewModel.myResponse.collectAsState()
    val id by viewModel.id.collectAsState()
    val response2 by viewModel.myResponse2.collectAsState()
    val response3 by viewModel.myResponse3.collectAsState()
    // viewModel.getCustomPost(id)
    viewModel.getPost2(id)

    val hashMap = HashMap<String , String>()
    hashMap["_sort"] = "id"
    hashMap["_order"] = "desc"
    viewModel.getCustomPost2(id,hashMap)
    // this is Launched Effect
    LaunchedEffect(key1 = id){
        if (response3.isSuccessful) {
            response3.body()?.forEach {
                Log.d("aaa", it.userId.toString())
                Log.d("aaa", it.id.toString())
                Log.d("aaa", it.title)
                Log.d("aaa", it.body)
                Log.d("aaa", "=======================")
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = CenterHorizontally)
            .padding(20.dp)
    ) {
        TextField(
            value = id,
            onValueChange = {
                viewModel.updateId(it)
            },
            placeholder = {
                Text(text = "Enter ID")
            },
            textStyle = MaterialTheme.typography.titleLarge,
        )
    }
    if (response2.isSuccessful) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Text(
                text = "${response2.body()?.userId}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "${response2.body()?.id}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = response2.body()?.title!!,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = response2.body()?.body!!,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(20.dp)

            )
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = response2.code().toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }

}