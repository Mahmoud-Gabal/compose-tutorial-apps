package com.example.mybookshelf.network

import com.example.mybookshelf.Data.IdData
import com.example.mybookshelf.book.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import retrofit2.http.Query


interface BaseService {
    @GET("volumes")
    suspend fun getIds(@Query("q") q : String) : Response<IdData>

    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id : String) : Response<Book>
}
