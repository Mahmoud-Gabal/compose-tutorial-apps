package com.example.navigationapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

@Composable
fun MyNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = FirstDistination.route) {
        composable(route = FirstDistination.route){
            FirstScreen(navController = navController)
        }
        composable(route = SecondDistination.route + "/{name}",
            arguments = listOf(
                navArgument(name = "name"){type = NavType.StringType}
            )
        ){entry ->
            val arg = entry.arguments?.getString("name")
            SecondScreen(name = arg, navController = navController)
        }
    }
}
@Composable
fun FirstScreen(modifier: Modifier = Modifier,navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        TextField(value = text, onValueChange ={
            text = it
        },
            modifier = modifier.padding(16.dp))
        Spacer(modifier = modifier.height(10.dp))
        Button(onClick = { navController.navigate(SecondDistination.route + "/$text") }) {
            Text(text = "Continue")
        }
    }
}

@Composable
fun SecondScreen(modifier: Modifier = Modifier,name : String?,navController: NavController) {
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Text(text = "Hello $name")
        Button(onClick = { navController.navigate(FirstDistination.route)}) {
            Text(text = "Back , $name")
        }
    }
}