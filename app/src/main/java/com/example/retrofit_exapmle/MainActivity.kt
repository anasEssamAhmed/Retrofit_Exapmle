package com.example.retrofit_exapmle

import android.os.Bundle
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        viewModel.getPost()
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

@Composable
fun ShowPost(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val response by viewModel.myResponse.collectAsState()
    if (response.isSuccessful) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${response.body()?.userId}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "${response.body()?.id}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = response.body()?.title!!,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = response.body()?.body!!,
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
                text = response.code().toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        }

    }
}