package com.example.catfactretrofit

data class JsonData(
    val current_page: Int =0,
    val `data`: List<Data> = emptyList(),
    val first_page_url: String = "",
    val from: Int =0,
    val last_page: Int =0,
    val last_page_url: String ="",
    val links: List<LinkX> = emptyList(),
    val next_page_url: String = "",
    val path: String = "",
    val per_page: Int =0,
    val prev_page_url: Any = 0,
    val to: Int =0,
    val total: Int=0
)