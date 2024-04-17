package com.example.navigationapp

import androidx.compose.runtime.Composable

interface Navigation {
    val route : String
}
object FirstDistination : Navigation {
    override val route: String
        get() = "First"
}
object SecondDistination : Navigation {
    override val route: String
        get() = "Second"
}