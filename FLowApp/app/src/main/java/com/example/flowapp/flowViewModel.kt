package com.example.flowapp

import android.view.View
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class flowViewModel() : ViewModel(){
     val timeFlow = flow<Int> {
        val start = 10
        var current = start
        emit(start)
        while (current > 0){
            delay(1000L)
            current --
            emit(current)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),10)
//    MutableStaeFlow is used for one event Change
    private val _countState = MutableStateFlow(0)
    val countState = _countState.asStateFlow()
//    MutableSharedFlow is used for more than one event (for more than one collector)
    private val _SharedCountState = MutableSharedFlow<Int>()
    val SharedCountState = _SharedCountState.asSharedFlow()

    init {
        timer()
//        shared flow is hot flow so we use it after collect or we use replay in initializing above
//        Sharedincrement()
//        viewModelScope.launch {
//            SharedCountState.collect{
//                println("Shared : First it is $it")
//            }
//        }
//        viewModelScope.launch {
//            SharedCountState.collect{
//                println("Shared : Second it is $it")
//            }
//        }
    }
    fun increment(){
        _countState.value +=1
    }
    fun Sharedincrement(n : Int ){
        viewModelScope.launch {
            _SharedCountState.emit(n)
        }
    }
     @OptIn(ExperimentalCoroutinesApi::class)
     private fun timer(){
        viewModelScope.launch {
//            timeFlow
//                .filter {
//                    it % 2 == 0
//                }
//                .map {
//                    it * it
//                }
//                .onEach {
//                    it / it
//                }
//                .collect{
//                println("the current time is $it")
//            }
//            val even_count = timeFlow
//                .filter { it % 2 == 0 }
//                .count()
//            val reduce_result = timeFlow
//                .reduce { accumulator, value ->
//                    accumulator + value
//                }
//            val fold_result = timeFlow
//                .fold(100) { accumulator, value ->
//                    accumulator + value
//                }
//            println("the even time is $even_count , the reduce is $reduce_result, the fold result is $fold_result")
//            timeFlow.onEach {
//                println("time is $it")
//            }.launchIn(viewModelScope)
//            ======================================================================


//            to do flow depending on flow

//            timeFlow.flatMapConcat { time ->
//                flow {
//                    emit(time)
//                    delay(500L)
//                    emit(time * time)
//                }
//            }.collect{time ->
//                println("depending flow time is $time")
//            }
//            val flow1 = (1..5).asFlow()
//                        flow1.flatMapConcat { time ->
//                flow {
//                    emit(time)
//                    delay(500L)
//                    emit(time * time)
//                }
//            }.collect{time ->
//                println("depending flow time is $time")
//            }
//            ==========================================================================
            val flow2 = flow<String> {
                delay(250)
                emit("fish")
                delay(100)
                emit("main dish")
                delay(1000)
                emit("dessert")
            }
            flow2.onEach {
                println("Flow : $it is delivered")
            }
                .collectLatest{
                    println("Flow : Start eating $it")
                    delay(1500)
                    println("Flow : finish eating $it")
                }
//                we can use buffer(),or conflate() to make onEach , collect work in separate coroutines
//                we can use  collectlatest to collect the latest emit
//                .buffer()
//                            .collect{
//                    println("Flow : Start eating $it")
//                    delay(1500)
//                    println("Flow : finish eating $it")
//                }
        }
    }
}