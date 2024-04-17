package com.example.adaptiveapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberWindowInfo():WindoInfo {
    val configuration = LocalConfiguration.current
    return WindoInfo(
        screenWidthInfo = when{
            configuration.screenWidthDp < 600 -> WindoInfo.WindowType.compat
            configuration.screenWidthDp < 840 -> WindoInfo.WindowType.medium
            else -> WindoInfo.WindowType.expanded
        },
        screenHeightInfo = when {
            configuration.screenHeightDp < 480 -> WindoInfo.WindowType.compat
            configuration.screenHeightDp < 900 -> WindoInfo.WindowType.medium
            else -> WindoInfo.WindowType.expanded
        },
        screenWidthDp = configuration.screenWidthDp.dp,
        screenHeightDp = configuration.screenHeightDp.dp
    )
}
data class WindoInfo(
    val screenWidthInfo : WindowType,
    val screenHeightInfo : WindowType,
    val screenWidthDp : Dp,
    val screenHeightDp : Dp
){
    sealed class WindowType{
        object compat : WindowType()
        object medium : WindowType()
        object expanded : WindowType()
    }


}