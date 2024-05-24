package com.bs23.androidtest103.data

import com.bs23.androidtest103.data.model.BookListResponse
import com.bs23.androidtest103.data.remote.ApiService
import com.bs23.androidtest103.utils.State
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    fun getBookList() = flow<State<BookListResponse>> {
        emit(State.Loading)
        try {
            val result = apiService.getBookList()
            emit(State.Success(result))
        } catch (exception: Exception) {
            emit(State.Error(exception))
        }
    }

}