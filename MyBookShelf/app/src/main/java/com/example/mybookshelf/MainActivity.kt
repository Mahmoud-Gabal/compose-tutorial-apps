package com.example.mybookshelf

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mybookshelf.BaseScreens.errorScreen
import com.example.mybookshelf.BaseScreens.homeScreen

import com.example.mybookshelf.BaseScreens.loadingScreen
import com.example.mybookshelf.BaseScreens.resultScreen

import com.example.mybookshelf.ViewModels.BaseViewModel
import com.example.mybookshelf.ViewModels.Status

import com.example.mybookshelf.book.Book
import com.example.mybookshelf.ui.theme.MyBookShelfTheme

@SuppressLint("MutableCollectionMutableState")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBookShelfTheme {
                // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        //                    var uiState : Status by remember {
                        //                        mutableStateOf(Status.loading)
                        //                    }
                        //                    val scope = rememberCoroutineScope()
                        //                    LaunchedEffect(key1 = true) {
                        //                        scope.launch (Dispatchers.IO){
                        //                            val response = try {
                        //                                  BaseObject.IdretrofirService.getIds("jazz history")
                        //                            }catch (e : IOException){
                        //                                uiState = Status.error
                        //                                return@launch
                        //                            }
                        //                            if (response.isSuccessful&&response.body()!=null){
                        //                                withContext(Dispatchers.Main){
                        //                                    uiState = Status.success(response.body()!!.items)
                        //                                }
                        //                            }
                        //                        }
                        //                    }

                        val baseView : BaseViewModel = viewModel(factory = BaseViewModel.Factory)
                        homeScreen(uiState = baseView.uiState)





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
    MyBookShelfTheme {
        Greeting("Android")
    }
}