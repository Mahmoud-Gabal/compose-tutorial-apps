package com.example.combinezipmergewithflowandstate.data

data class User(
    val username : String ?= null,
    val description : String ?= null,
    val profilePicUrl : String ?= null
)

data class Post(
    val username: String?= null ,
    val description: String?= null,
    val imageUrl : String?= null
)

data class ProfileState(
    val username: String?= null,
    val description: String?= null,
    val profilePicUrl : String?= null,
    val posts : List<Post> = emptyList()
)