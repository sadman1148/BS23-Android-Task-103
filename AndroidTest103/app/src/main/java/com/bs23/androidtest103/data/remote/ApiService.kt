package com.bs23.androidtest103.data.remote

import com.bs23.androidtest103.data.model.BookListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(ApiUrl.BOOKLIST_ENDPOINT)
    suspend fun getBookList() : BookListResponse
}