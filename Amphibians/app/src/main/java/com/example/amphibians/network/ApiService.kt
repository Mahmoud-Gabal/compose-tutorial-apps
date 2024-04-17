package com.example.amphibians.network

import com.example.amphibians.Data.dataItem
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET



interface AmphiService {
    @GET("/amphibians")
    suspend fun getAmphi(): List<dataItem>
}
