package com.toptal.domain.helper

import com.toptal.domain.exception.DomainError

/**
 * Result class for handling success and failure
 */
sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val error: DomainError) : Result<Nothing>()

    inline fun onSuccess(action: (T) -> Unit): Result<T> {
        if (this is Success) action(data)
        return this
    }

    inline fun onFailure(action: (DomainError) -> Unit): Result<T> {
        if (this is Failure) action(error)
        return this
    }

    inline fun <R> map(transform: (T) -> R): Result<R> {
        return when (this) {
            is Success -> Success(transform(data))
            is Failure -> this
        }
    }

    fun getOrNull(): T? {
        return when (this) {
            is Success -> data
            is Failure -> null
        }
    }

    fun exceptionOrNull(): DomainError? {
        return when (this) {
            is Success -> null
            is Failure -> error
        }
    }

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Failure
}
