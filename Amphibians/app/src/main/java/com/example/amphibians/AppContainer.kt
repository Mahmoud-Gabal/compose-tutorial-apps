package com.example.amphibians

import com.example.amphibians.Repo.ApiRepositery
import com.example.amphibians.Repo.NetworkApiRepositery
import com.example.amphibians.network.AmphiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val amphiRepo : ApiRepositery
}
class DefaultContainer : AppContainer{

    private val Base_Url = " https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(Base_Url)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService : AmphiService by lazy {
        retrofit.create(AmphiService::class.java)
    }
    override val amphiRepo: ApiRepositery
        get() = NetworkApiRepositery(retrofitService)
}

