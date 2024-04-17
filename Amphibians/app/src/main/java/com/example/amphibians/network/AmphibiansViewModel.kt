package com.example.amphibians.network

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.Data.dataItem
import com.example.amphibians.DefaultContainer
import com.example.amphibians.MyApllication
import com.example.amphibians.Repo.ApiRepositery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException

sealed interface Status {
    data class success( val data : List<dataItem>) : Status
    object loading : Status
    object error : Status
}
class AmphibiansViewModel(private val amphiRepo: ApiRepositery) : ViewModel(){
    var uiState : Status by mutableStateOf(Status.loading)
        private set
    init {
        getData()
    }
    fun getData(){
        viewModelScope.launch{
            try {
                val list = amphiRepo.getAmphiPhotos()
                uiState = Status.success(list)
            }catch (e : IOException){
                uiState = Status.error
            }
        }
    }
    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application  = (this[APPLICATION_KEY] as MyApllication)
                val amphiRepo = application.container.amphiRepo
                AmphibiansViewModel(amphiRepo = amphiRepo)
            }
        }
    }

}