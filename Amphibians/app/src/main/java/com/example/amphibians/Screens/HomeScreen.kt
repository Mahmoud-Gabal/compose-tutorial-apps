package com.example.amphibians.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.Data.dataItem
import com.example.amphibians.R
import com.example.amphibians.network.Status


@Composable
fun HomeScreen(uiState : Status) {
    when(uiState){
        is Status.loading -> loadingScreen()
        is Status.error -> errorScreen()
        is Status.success -> resultScreen(data = uiState.data)

    }
}
@Composable
fun loadingScreen(modifier: Modifier = Modifier) {
        Column(modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Loading !!",
                fontSize = 40.sp)
        }

}

@Composable
fun errorScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Failed !!",
            fontSize = 40.sp)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun resultScreen(modifier: Modifier = Modifier,data: List<dataItem>) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Amphibians")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = Color.Black
                )
            )
        }
    ){
        LazyColumn(modifier = modifier
            .fillMaxSize()
            .padding(it)) {
            items(data) { item: dataItem ->
                ImageCard(dataItem = item)
            }
        }
    }
}

@Composable
fun ImageCard(dataItem: dataItem,modifier : Modifier = Modifier) {
    Card (modifier = modifier
        .fillMaxWidth()
        .padding(12.dp),
//        .aspectRatio(1.5f),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)){
            Text(
                text = "${dataItem.name}(${dataItem.type})",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(dataItem.img_src)
                    .placeholder(R.drawable.baseline_downloading_24)
                    .error(R.drawable.baseline_error_outline_24)
                    .crossfade(true)
                    .build(),
                contentDescription = "photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = dataItem.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold

            )
    }
}