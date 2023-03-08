package com.mahmoudhamdyae.domain.models

sealed class Result<T : Any> {
    class Success<T: Any>(val data: T) : Result<T>()
    class Error<T: Any>(val message: String) : Result<T>()
}