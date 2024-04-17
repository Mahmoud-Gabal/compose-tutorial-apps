package com.example.adaptiveapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import com.example.adaptiveapp.ui.theme.AdaptiveAppTheme
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val devicePostureFlow = foldCases(this,lifecycleScope)
        setContent {
            AdaptiveAppTheme {
                // A surface container using the 'background' color from the theme
//                    val windowInfo = rememberWindowInfo()
                    val windowSize = calculateWindowSizeClass(activity = this)
                    val devicePosture = devicePostureFlow.collectAsState().value
                    if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact){

                            LazyColumn (modifier = Modifier.fillMaxSize()){
                                items(20){
                                    Text(text = "item $it",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.Cyan)
                                            .padding(5.dp))
                                }

                                items(20) {
                                    Text(
                                        text = "item $it",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.Green)
                                            .padding(5.dp)
                                    )
                                }

                            }
                    }else if (windowSize.widthSizeClass == WindowWidthSizeClass.Medium){
                        Row (modifier = Modifier.fillMaxSize()){
                            LazyColumn (modifier = Modifier.weight(1f)){
                                items(20){
                                    Text(text = "item $it",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.Cyan)
                                            .padding(5.dp))
                                }
                            }
                            LazyColumn (modifier = Modifier.weight(1f)){
                                items(20){
                                    Text(text = "item $it",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color.Green)
                                            .padding(5.dp))
                                }
                            }
                        }
                    }else{
                        if (devicePosture is DevicePosture.Separating){
                            Row (modifier = Modifier.fillMaxSize()){
                                LazyColumn (modifier = Modifier.weight(1f)){
                                    items(20){
                                        Text(text = "item $it",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(Color.Cyan)
                                                .padding(5.dp))
                                    }
                                }
                                LazyColumn (modifier = Modifier.weight(1f)){
                                    items(20){
                                        Text(text = "item $it",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(Color.Green)
                                                .padding(5.dp))
                                    }
                                }
                            }
                        }else{
                            Row (modifier = Modifier.fillMaxSize()){
                                LazyColumn (modifier = Modifier.weight(1f)){
                                    items(20){
                                        Text(text = "item $it",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(Color.Cyan)
                                                .padding(5.dp))
                                    }
                                }
                                LazyColumn (modifier = Modifier.weight(1f)){
                                    items(20){
                                        Text(text = "item $it",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(Color.Green)
                                                .padding(5.dp))
                                    }
                                }
                            }
                        }
                    }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdaptiveAppTheme {
        Greeting("Android")
    }
}