package com.omerakkoyun.mybaseapp1.models

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null,
    val networkError: Boolean = false) {


    class Success<T>(data: T) : NetworkResult<T>(data = data, message = "", networkError = false)
    class Error<T>(networkError: Boolean, message: String?) : NetworkResult<T>(data = null, message = message, networkError = networkError)
}

