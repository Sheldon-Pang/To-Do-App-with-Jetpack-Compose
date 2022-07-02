package com.example.to_doappwithjetpackcompose.util

sealed class RequestState<out T> {
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val error: Throwable) : RequestState<Nothing>()
}


