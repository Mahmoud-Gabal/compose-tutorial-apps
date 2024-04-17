package com.example.amphibians.Repo

import com.example.amphibians.Data.dataItem

import com.example.amphibians.network.AmphiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ApiRepositery {
    suspend fun getAmphiPhotos(): List<dataItem>
}
class NetworkApiRepositery(private val retrofitService :AmphiService ): ApiRepositery {
    override suspend fun getAmphiPhotos(): List<dataItem> {
        return retrofitService.getAmphi()
    }
}