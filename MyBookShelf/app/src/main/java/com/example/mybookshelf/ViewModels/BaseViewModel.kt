package com.example.mybookshelf.ViewModels

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mybookshelf.Application.MyApplication
import com.example.mybookshelf.RepoAndContainers.bookRepo
import com.example.mybookshelf.RepoAndContainers.networkBookRepo
import com.example.mybookshelf.book.Book
import kotlinx.coroutines.launch
import okio.IOException

sealed interface Status {
    data class success( val data : MutableList<Book>) : Status
    object loading : Status
    object error : Status
}

@SuppressLint("MutableCollectionMutableState")
class BaseViewModel(private val bookRepo: bookRepo) : ViewModel(){
    var uiState : Status by mutableStateOf(Status.loading)
        private set
    var ids : MutableList<String> by mutableStateOf(mutableListOf())
        private set
    var books by mutableStateOf(mutableListOf<Book>())
        private set
    init {
        getUrls()
    }
    fun getUrls(){
        viewModelScope.launch {
            try {
                val response = bookRepo.getStringIds("jazz history")
                if (response.isSuccessful&&response.body()!=null){
                    for (i in response.body()!!.items.indices){
                        ids.add(response.body()!!.items[i].id)
                    }
                    for (id in ids){
                        var bookRes = bookRepo.getStringBook(id)
                        if (bookRes.isSuccessful && bookRes.body() != null) {
                            books.add(bookRes.body()!!)

                        }
                    }
                    uiState = Status.success(books)
                }
            }catch (e : IOException){
                uiState = Status.error
            }
        }
    }
    companion object{
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MyApplication)
                val container = application.container
                val bookRepo = container.bookRepo
                BaseViewModel(bookRepo)
            }
        }
    }

}
