package com.example.amphibians.Data

import kotlinx.serialization.Serializable

@Serializable
data class dataItem(
    val description: String,
    val img_src: String,
    val name: String,
    val type: String
)