package com.example.mymarsphotosserialization

import com.example.mymarsphotosserialization.ui.theme.MyMarsPhotosSERIALIZATIONTheme
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymarsphotosserialization.ui.theme.MyMarsPhotosSERIALIZATIONTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : ComponentActivity() {
    private var myData by
    mutableStateOf(Data())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMarsPhotosSERIALIZATIONTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize())
                    {
                        Text(text = "you downloaded ${myData.size} photos", modifier = Modifier.padding(16.dp))
                        Button(onClick = { SendRequest() }) {
                            Text(text = "Download Photos")
                        }
                    }

                }
            }
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun SendRequest(

    ){
        GlobalScope.launch (Dispatchers.IO){
            try{
                val response = MarsApi.retrofitService.getPhotos()
                withContext(Dispatchers.Main){
                    myData = response
                }
            }catch (e : IOException){
                Toast.makeText(applicationContext, "Error : ${e.message}", Toast.LENGTH_SHORT).show()

            }



        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}