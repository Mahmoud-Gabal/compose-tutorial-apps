package com.example.flowapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flowapp.ui.theme.FlowAppTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter

class MainActivity : ComponentActivity() {
    private var sharedTxt by mutableStateOf(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = viewModel<flowViewModel>()
                    val time = viewModel.timeFlow.collectAsState(initial = 10)
                    val count = viewModel.countState.collectAsState(0)
                    Box (modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        Column() {
                            Text(
                                text = "Timer : " + time.value.toString(),
                                modifier = Modifier.padding(16.dp)
                            )
                            Button(onClick = { viewModel.increment() }, modifier = Modifier.padding(16.dp)) {
                                Text(text = "Counter : ${count.value}")
                            }
                            LaunchedEffect(key1 = true) {
                                viewModel.SharedCountState.collect{
                                    sharedTxt = it
                                }
                            }
                            viewModel.Sharedincrement(count.value)
                            Text(text = "Shared is $sharedTxt ")
                        }
                        

                    }
                }
            }
        }
    }
}

