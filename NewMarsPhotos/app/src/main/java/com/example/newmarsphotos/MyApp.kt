package com.example.newmarsphotos

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyApp() {
    val marsViewModel : MarsViewModel = viewModel()
    homeScreen(state = marsViewModel.state,marsViewModel::getData)
}