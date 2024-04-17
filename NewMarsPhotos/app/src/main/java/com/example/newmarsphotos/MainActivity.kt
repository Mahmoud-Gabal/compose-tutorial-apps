package com.example.newmarsphotos

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview

import com.example.newmarsphotos.ui.theme.NewMarsPhotosTheme
import androidx.lifecycle.viewmodel.compose.viewModel

private var showButton by mutableStateOf(true)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewMarsPhotosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var showButtonPage by remember {
                        mutableStateOf(true)
                    }
                    if (showButtonPage){
                        ButtonPage {
                            showButtonPage = !showButtonPage
                        }
                    }else{
                        MyApp()
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonPage(showButtonPage : () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = showButtonPage) {
            Text(text = "Download")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewMarsPhotosTheme {

    }
}