@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateRect
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val drawerstate = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                var onSelectedIndex by rememberSaveable() {
                    mutableStateOf(0)
                }
                val listofItems = listOf(0,1,2)
                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet {
                            Spacer(modifier = Modifier.height(15.dp))
                            NavigationDrawerItem(
                                label = { Text(text = "Home") },
                                selected = listofItems[0]==onSelectedIndex,
                                icon = {
                                    Icon(
                                        imageVector =if (listofItems[0]!=onSelectedIndex) Icons.Default.Home else Icons.Filled.Home,
                                        contentDescription =null
                                    )
                                },
                                onClick = {
                                    onSelectedIndex = listofItems[0]

                                },
                                badge = { Text(text = "50")},
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding))
                            NavigationDrawerItem(
                                label = { Text(text = "Profile") },
                                selected = listofItems[1]==onSelectedIndex,
                                icon ={
                                    Icon(
                                        imageVector =if (listofItems[1]!=onSelectedIndex) Icons.Default.Person else Icons.Filled.Person,
                                        contentDescription =null
                                    )
                                },
                                onClick = {
                                    onSelectedIndex = listofItems[1]
                                },
                                badge = { Text(text = "50")},
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding))
                            NavigationDrawerItem(
                                label = { Text(text = "Settings") },
                                selected = listofItems[2]==onSelectedIndex,
                                icon ={
                                    Icon(
                                        imageVector =if (listofItems[2]!=onSelectedIndex) Icons.Default.Settings else Icons.Filled.Settings,
                                        contentDescription =null
                                    )
                                },
                                onClick = {
                                    onSelectedIndex = listofItems[2]
                                },
                                badge = { Text(text = "50")},
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding))
                        }
                    },
                    drawerState = drawerstate
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = "ToDo App")},
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerstate.open()
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription =null,
                                            modifier = Modifier.padding(horizontal = 10.dp))
                                    }},
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = Color.Blue,
                                    navigationIconContentColor = Color.White,
                                    titleContentColor = Color.White
                                )
                            )
                        }
                    ) {
                        var parentSize by remember {
                            mutableStateOf(Size.Zero)
                        }
                        Column (modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .onGloballyPositioned { coordinates ->
                                parentSize = coordinates.size.toSize()
                            }){
                            var isVisible by remember {
                                mutableStateOf(false)
                            }
                            var isGreen by remember {
                                mutableStateOf(false)
                            }
                            var isOverWidth by remember {
                                mutableStateOf(false)
                            }
                            var isOverHeight by remember {
                                mutableStateOf(false)
                            }
                            var isCircle by remember {
                                mutableStateOf(false)
                            }
                            val transition = updateTransition(
                                targetState = isCircle, label = "On transition"
                            )
                            val CircleAnimation by transition.animateDp (
                                transitionSpec = { tween(durationMillis = 1000) },
                                label = "transition of border",
                                targetValueByState = {isCircle ->
                                    if (isCircle) 100.dp else 0.dp
                                }
                            )
                            val animatedColor by animateColorAsState(
                                targetValue = if (isGreen) Color.Green else Color.Red,
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                            val animatedWidth by animateFloatAsState(
                                targetValue = if (isOverWidth) parentSize.width else 200.toFloat(),
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow
                                )
                            )
                            val animatedHeight by animateFloatAsState(
                                targetValue = if (isOverHeight) parentSize.height else 200.toFloat(),
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow
                                )
                            )
                            val infiniteTransition = rememberInfiniteTransition()
                            val infiniteColor by infiniteTransition.animateColor(
                                initialValue = Color.Red,
                                targetValue = Color.Green,
                                animationSpec = InfiniteRepeatableSpec(
                                    animation = tween(durationMillis = 2000),
                                    repeatMode = RepeatMode.Reverse
                                )
                            )
                            Column {
                                Row (){
                                    Button(
                                        onClick = { isVisible = !isVisible }
                                    ) {
                                        Text(text = if (isVisible) "Show Infinite Box" else "Show Custom Box")
                                    }
                                    Button(
                                        onClick = { isGreen = !isGreen }

                                    ) {
                                        Text(text = if (isGreen) "Make it Red" else "Make it Green")
                                    }
                                    Button(
                                        onClick = { isCircle = !isCircle }

                                    ) {
                                        Text(text = if (isCircle) "Delete Circle" else "Make Circle")
                                    }
                                }
                                Row {
                                    Button(
                                        onClick = { isOverWidth = !isOverWidth }
                                    ) {
                                        Text(text = if (isOverWidth) "Minimize Width" else "Maxmize Width")
                                    }
                                    Button(
                                        onClick = { isOverHeight = !isOverHeight }
                                    ) {
                                        Text(text = if (isOverHeight) "Minimize Height" else "Maxmize Height")
                                    }
                                }
                            }
                            AnimatedContent(targetState = isVisible,
                            transitionSpec = {
                                slideInVertically(
                                    initialOffsetY = {fullHeight: Int -> -30 }
                                ) + fadeIn() with slideOutVertically(
                                    targetOffsetY = {fullHeight: Int ->  -30}
                                )+fadeOut()
                            }
                            ) {isVisible ->
                                if (isVisible){
                                    Box(
                                        modifier =
                                        Modifier
                                            .size(
                                                width = animatedWidth.dp,
                                                height = animatedHeight.dp
                                            )
                                            .padding(5.dp)
                                            .clip(RoundedCornerShape(CircleAnimation))
                                            .background(animatedColor)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }else {
                                    Box(
                                        modifier =
                                        Modifier
                                            .size(
                                                width = parentSize.width.dp,
                                                height = parentSize.height.dp
                                            )
                                            .padding(5.dp)
                                            .clip(RoundedCornerShape(100.dp))
                                            .background(infiniteColor)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                            }
//                            AnimatedVisibility(
//                                visible = isVisible,
//                                enter = slideInVertically(
//                                    initialOffsetY = {fullHeight: Int -> -30 }
//                                )+ fadeIn(),
//                                exit = slideOutVertically(
//                                    targetOffsetY = {fullHeight: Int -> -30 }
//                                )+ fadeOut()
//                            ) {
//                                Box(
//                                    modifier =
//                                    Modifier
//                                        .size(width = animatedWidth.dp, height = animatedHeight.dp)
//                                        .padding(5.dp)
//                                        .clip(RoundedCornerShape(CircleAnimation))
//                                        .background(animatedColor)
//                                        .align(Alignment.CenterHorizontally)
//                                )
//                            }
                        }
                    }
                }
            }
        }
    }
}


//
//private fun NamesList() = listOf(
//    "Item 1" to R.drawable.fc4_self_massage,
//    "Item 1" to R.drawable.fc1_short_mantras,
//    "Item 1" to R.drawable.fc2_nature_meditations,
//    "Item 1" to R.drawable.fc3_stress_and_anxiety,
//    ).map { pairData(it.first,it.second) }
//private data class pairData(
//    val name: String,
//    @DrawableRes val drawable: Int
//)
//@Composable
//fun SurveyItem(modifier: Modifier = Modifier,name:String,drawable : Int) {
//    Surface(
//        shape = MaterialTheme.shapes.medium,
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.background(Color.LightGray)
//            ) {
//            Image(
//                painter = painterResource(id = drawable),
//                contentDescription = null,
//                modifier = modifier
//                    .size(65.dp)
//                    .weight(1f)
//                    .wrapContentWidth(Alignment.Start)
//            )
//            Text(text = name)
//            RadioButton(selected = false, onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 8.dp))
//        }
//    }
//}



//fun SurveyList(modifier: Modifier = Modifier) {
//    LazyColumn(
//        modifier = modifier
//    ) {
//        items(NamesList()){item ->
//            SurveyItem(name = item.name, drawable =item.drawable )
//            Spacer(modifier = Modifier.height(8.dp))
//        }
//    }
//}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GridPreview() {
    MyApplicationTheme {
        val drawerstate = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var onSelectedIndex by rememberSaveable() {
            mutableStateOf(0)
        }
        val listofItems = listOf(0,1,2)
        ModalNavigationDrawer(
            drawerContent = {
                    ModalDrawerSheet {
                        Spacer(modifier = Modifier.height(15.dp))
                        NavigationDrawerItem(
                            label = { Text(text = "Home") },
                            selected = listofItems[0]==onSelectedIndex,
                            icon = {
                                   Icon(
                                       imageVector =if (listofItems[0]!=onSelectedIndex) Icons.Default.Home else Icons.Filled.Home,
                                       contentDescription =null
                                   )
                            },
                            onClick = {
                                onSelectedIndex = listofItems[0]

                            },
                            badge = { Text(text = "50")},
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding))
                        NavigationDrawerItem(
                            label = { Text(text = "Profile") },
                            selected = listofItems[1]==onSelectedIndex,
                            icon ={
                                Icon(
                                    imageVector =if (listofItems[1]!=onSelectedIndex) Icons.Default.Person else Icons.Filled.Person,
                                    contentDescription =null
                                )
                               },
                            onClick = {
                                onSelectedIndex = listofItems[1]
                            },
                            badge = { Text(text = "50")},
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding))
                        NavigationDrawerItem(
                            label = { Text(text = "Settings") },
                            selected = listofItems[2]==onSelectedIndex,
                            icon ={
                                Icon(
                                    imageVector =if (listofItems[2]!=onSelectedIndex) Icons.Default.Settings else Icons.Filled.Settings,
                                    contentDescription =null
                                )
                              },
                            onClick = {
                                onSelectedIndex = listofItems[2]
                            },
                            badge = { Text(text = "50")},
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding))
                    }
            },
            drawerState = drawerstate
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "ToDo App")},
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerstate.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription =null,
                                    modifier = Modifier.padding(horizontal = 10.dp))
                            }},
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Blue,
                            navigationIconContentColor = Color.White,
                            titleContentColor = Color.White
                        )
                    )
                }
            ) {
                Column (modifier = Modifier
                    .fillMaxSize()
                    .padding(it)){
                    var isVisible by remember {
                        mutableStateOf(false)
                    }
                    Button(onClick = { isVisible = !isVisible}, modifier = Modifier.padding(10.dp)) {
                        Text(text = "Show Box")
                    }
                    AnimatedVisibility(
                        visible = isVisible,
                        enter = slideInHorizontally(animationSpec = tween(
                            durationMillis = 1000
                        ))+ fadeIn() ,
                        exit = slideOutHorizontally(animationSpec = tween(
                            durationMillis = 2000
                        ))+ fadeOut()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(200.dp)
                                .padding(10.dp)
                                .background(Color.Red)
                        )
                    }
                }
            }
        }
    }
}