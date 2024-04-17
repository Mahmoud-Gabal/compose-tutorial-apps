package com.example.newmarsphotos

import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun homeScreen(state : Cases,retryAction : () -> Unit) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        when(state){
            is Cases.loading -> loadingScreen()
            is Cases.error -> ErrorScreen(retryAction = retryAction)
            is Cases.success -> PhotoGrid(data = state.data)

        }
    }
}
@Composable
fun loadingScreen() {
    Text(text = "Loading!!")
}
@Composable
fun stableScreen() {
    Text(text = "stable")
}
@Composable
fun ErrorScreen(retryAction : () -> Unit) {
    Column {
        Text(text = "Failed To download!!", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = "Retry")
        }
    }
}
@Composable
fun PhotoGrid(data : Data) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier.fillMaxSize()) {
        items(data, key = {item: DataItem -> item.id }){
                item: DataItem ->
            PhotoCard(myData = item)
        }
    }

}
@Composable
fun PhotoCard(myData : DataItem) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(1.5f),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(myData.img_src)
                .crossfade(true)
                .error(R.drawable.error)
                .placeholder(R.drawable.baseline_downloading_24)
                .build(),
            contentDescription = "MarsPhotos",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}