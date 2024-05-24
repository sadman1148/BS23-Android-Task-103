package com.bs23.androidtest103.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bs23.androidtest103.data.Repository
import com.bs23.androidtest103.data.model.BookListResponse
import com.bs23.androidtest103.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val bookListLiveData: LiveData<State<BookListResponse>> get() = _bookListMutableLiveData
    private val _bookListMutableLiveData = MutableLiveData<State<BookListResponse>>()

    fun getBookList() {
        repository.getBookList().onEach {
            _bookListMutableLiveData.value = it
        }.launchIn(viewModelScope)
    }
}