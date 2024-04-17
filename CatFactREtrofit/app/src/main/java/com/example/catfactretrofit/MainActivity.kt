package com.example.catfactretrofit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catfactretrofit.ui.theme.CatFactREtrofitTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : ComponentActivity() {
//    private var fact by
//        mutableStateOf(Data())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatFactREtrofitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
//                            MyRequest()
                        },
                    color = MaterialTheme.colorScheme.background
                ) {
//                    MyRequest()
                    val Context = LocalContext.current
                      var fact by remember {
                          mutableStateOf(JsonData())
                      }
                    val scope = rememberCoroutineScope()
                    LaunchedEffect(key1 = true) {
                        scope.launch(Dispatchers.IO) {
                            val response = try {
                                ApiServiceobject.retrofitService.getFact()

                            }catch (e : IOException){
                                Toast.makeText(Context, "Error : ${e.message}", Toast.LENGTH_SHORT).show()
                                return@launch
                            }
                            if (response.isSuccessful && response.body() != null){
                                withContext(Dispatchers.Main){
                                    fact = response.body()!!
                                }
                            }
                        }

                    }
                        MyApp(fact)
                }
            }
        }
    }
//    @OptIn(DelicateCoroutinesApi::class)
//    private fun MyRequest(){
//            GlobalScope.launch(Dispatchers.IO) {
//                val response = try {
//                    ApiServiceobject.retrofitService.getFact()
//
//                }catch (e : IOException){
//                    Toast.makeText(applicationContext, "Error : ${e.message}", Toast.LENGTH_SHORT).show()
//                    return@launch
//                }
//                if (response.isSuccessful && response.body() != null){
//                    withContext(Dispatchers.Main){
//                       fact = response.body()!!
//                    }
//                }
//            }
//    }
}

@Composable
fun MyApp(fact: JsonData, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Fact are :", modifier = modifier.padding(16.dp))
        LazyColumn {
            items(fact.data) {
                Text(
                    text = it.fact,
                    fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp)
                )

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
    CatFactREtrofitTheme {
        Greeting("Android")
    }
}