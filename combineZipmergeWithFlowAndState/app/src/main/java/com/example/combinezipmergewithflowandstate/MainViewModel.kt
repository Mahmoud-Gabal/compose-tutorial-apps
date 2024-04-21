package com.example.combinezipmergewithflowandstate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.combinezipmergewithflowandstate.data.Post
import com.example.combinezipmergewithflowandstate.data.ProfileState
import com.example.combinezipmergewithflowandstate.data.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip

class MainViewModel: ViewModel() {

    private val isAuth = MutableStateFlow<Boolean>(true)
    private val user = MutableStateFlow<User?>(null)
    private val posts = MutableStateFlow(emptyList<Post>())

    private val _profileState = MutableStateFlow<ProfileState?>(null)
    private val profileState = _profileState.asStateFlow()

    var text by mutableStateOf("")
        private set
    var text2 by mutableStateOf("")
        private set
    private val flow1 = (1..10).asFlow().onEach { delay(1000L) }
    private val flow2 = (10..100).asFlow().onEach { delay(300L) }

    //    combine is used when a stateFlow is depending on another stateflow
    init {
        user.combine(isAuth) { user, isAuth ->
            if (isAuth) user else null
        }.combine(posts) { user, posts ->
            user?.let {
                _profileState.value = profileState.value?.copy(
                    username = user.username,
                    description = user.description,
                    profilePicUrl = user.profilePicUrl,
                    posts = posts
                )
            }
        }.launchIn(viewModelScope)
//        zip is used to concatinate two emits from two flow and do not care about delay of each other so
//        first emit with first emit and so on
        flow1.zip(flow2) { n1, n2 ->
            text += "($n1,$n2)\n"
        }.launchIn(viewModelScope)
//        merge is making them as one flow and do the latest emit has finished from them
        merge(flow1, flow2).onEach {
            text2 += "($it)\n"
        }.launchIn(viewModelScope)
    }
}