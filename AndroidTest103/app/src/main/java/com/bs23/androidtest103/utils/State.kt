package com.bs23.androidtest103.utils

import java.lang.Exception

sealed class State<out T> {
    data class Success<out T>(val data: T) : State<T>()
    data class Error(val exception: Exception) : State<Nothing>()
    object Loading : State<Nothing>()
}