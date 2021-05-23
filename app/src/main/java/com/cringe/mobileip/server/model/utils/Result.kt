package com.cringe.mobileip.server.model.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Exception(val exception: java.lang.Exception) : Result<Nothing>()
    data class Failure<out T : Any>(val data: T) : Result<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Exception -> "Error[exception=$exception]"
            is Failure<*> -> "Failure[data=$data]"
        }
    }
}