package com.example.catfactretrofit

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val BASE_URL = "https://catfact.ninja"

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface apiService {
    @GET("facts")
    suspend fun getFact() : Response<JsonData>
}
object ApiServiceobject {
    val retrofitService by lazy {
        retrofit.create(apiService::class.java)
    }
}