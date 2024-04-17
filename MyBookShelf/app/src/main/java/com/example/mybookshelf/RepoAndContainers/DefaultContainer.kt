package com.example.mybookshelf.RepoAndContainers

import com.example.mybookshelf.network.BaseService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface appContainer{
   val  bookRepo : bookRepo
}
class DefaultContainer : appContainer {
    private val Base_Url = "https://www.googleapis.com/books/v1/"

    private val Idretrofit = Retrofit.Builder()
        .baseUrl(Base_Url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val IdretrofirService : BaseService by lazy {
        Idretrofit.create(BaseService::class.java)
    }

    override val bookRepo: bookRepo
        get() = networkBookRepo(IdretrofirService)
}