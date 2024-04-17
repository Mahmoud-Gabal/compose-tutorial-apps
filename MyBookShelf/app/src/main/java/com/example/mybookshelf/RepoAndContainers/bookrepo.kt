package com.example.mybookshelf.RepoAndContainers

import com.example.mybookshelf.Data.IdData
import com.example.mybookshelf.book.Book
import com.example.mybookshelf.network.BaseService
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface bookRepo{
    suspend fun getStringIds(@Query("q") q : String) : Response<IdData>
    suspend fun getStringBook(@Path("id") id : String) : Response<Book>
}
class networkBookRepo(private val IdretrofirService: BaseService) : bookRepo{
    override suspend fun getStringIds(q: String ): Response<IdData> {
        return IdretrofirService.getIds(q)
    }

    override suspend fun getStringBook(id: String): Response<Book> {
        return IdretrofirService.getBook(id)
    }
}