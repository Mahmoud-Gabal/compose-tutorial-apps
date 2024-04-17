package com.example.newmarsphotos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException

sealed interface Cases {
    data class success(val data: Data) : Cases
    object loading : Cases
    object error : Cases

}
class MarsViewModel():ViewModel() {
     var state : Cases by mutableStateOf(Cases.loading)
         private set
    init {

            getData()

    }
    fun getData(){
        state = Cases.loading
        viewModelScope.launch (Dispatchers.IO){
            val response = try {
                  MarsApi.retrofitService.getPhotos()
            }catch (e : IOException){
                state = Cases.error
                return@launch
            }
            if (response.isSuccessful&&response.body()!= null){
                state = Cases.success(response.body()!!)
            }
        }
    }

}