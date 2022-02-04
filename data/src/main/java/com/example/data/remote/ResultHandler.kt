package com.example.data.remote

sealed class ResultHandler<out T> {
    class Success<T>(val data: T) : ResultHandler<T>()
    class NetworkError : ResultHandler<Nothing>()
    class HttpError(val code: Int, val message: String) : ResultHandler<Nothing>()
    class GenericError(val message: String) : ResultHandler<Nothing>()
}