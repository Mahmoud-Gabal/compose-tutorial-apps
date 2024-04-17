package com.example.mybookshelf.book

data class Book(
    val accessInfo: AccessInfo,
    val etag: String,
    val id: String,
    val kind: String,
    val saleInfo: SaleInfo,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)