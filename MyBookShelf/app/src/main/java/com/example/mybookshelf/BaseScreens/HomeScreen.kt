package com.example.mybookshelf.BaseScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybookshelf.Navigation.FirstDistination
import com.example.mybookshelf.Navigation.SecondDistination
import com.example.mybookshelf.R
import com.example.mybookshelf.ViewModels.Status
import com.example.mybookshelf.book.Book


@Composable
fun homeScreen(uiState: Status) {
    when (uiState){
        is Status.error -> errorScreen()
        is Status.loading -> loadingScreen()
        is Status.success -> resultScreen(books = uiState.data)
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
fun resultScreen( books: MutableList<Book>,modifier : Modifier = Modifier) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Book Shelf")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = Color.Black
                )
            )
        }
    ){

        myNavHost(paddingValues = it, books = books)
    }
}
@Composable
fun myNavHost(paddingValues: PaddingValues,books: MutableList<Book>){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = FirstDistination.route){
        composable(route = FirstDistination.route){
            lazyScreen(paddingValues = paddingValues, books = books, navController = navController)
        }
        composable(route = SecondDistination.route+ "/{title}" + "/{authors}" + "/{des}",
            arguments = listOf(
                navArgument(name = "title"){type = NavType.StringType},
                navArgument(name = "authors"){type = NavType.StringType},
                navArgument(name = "des"){type = NavType.StringType}
            )
        ){entry ->
            val title = entry.arguments?.getString("title")
            val authors = entry.arguments?.getString("authors")
            val des = entry.arguments?.getString("des")
            BookDetails(title = title, author = authors, des = des,paddingValues = paddingValues ,navController = navController)
        }
    }
}
@Composable
fun lazyScreen(modifier: Modifier = Modifier,paddingValues: PaddingValues,books: MutableList<Book>,navController: NavController) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        columns = GridCells.Adaptive(minSize = 150.dp)
    ) {
        items(books){book ->
            imgCard(book = book, navController = navController)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun imgCard(modifier: Modifier = Modifier,book : Book,navController: NavController) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)
        .aspectRatio(.7f),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {
            navController.navigate(
                SecondDistination.route + "/${book.volumeInfo.title}" + "/${book.volumeInfo.authors}" + "/${book.volumeInfo.description}"
            )
        }
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.volumeInfo.imageLinks.thumbnail.replace("http","https"))
                .placeholder(R.drawable.baseline_downloading_24)
                .error(R.drawable.baseline_error_outline_24)
                .crossfade(true)
                .build(),
            contentDescription = "A book photo",
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
fun BookDetails(
    title: String?,
    author: String?,
    des: String?,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(paddingValues)) {
        item {
            Text(
                text = "$title",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                textAlign = TextAlign.Center
            )
        }
        item {
            Text(
                text = "Written by : \n $author",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                textAlign = TextAlign.Start
            )
        }
        item {
            Text(text = "Description : \n $des",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                textAlign = TextAlign.Start)
        }
        item {
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){
                Button(
                    onClick = { navController.navigate(FirstDistination.route) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Go Back")
                }
            }
        }
    }
}

