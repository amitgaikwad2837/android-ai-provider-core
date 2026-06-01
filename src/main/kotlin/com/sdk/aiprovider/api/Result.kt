package com.sdk.aiprovider.api

/**
 * Result type for operations that can succeed or fail.
 *
 * Platform: Android
 * Category: Shared Foundation
 * Use sealed class pattern for type-safe error handling.
 */
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()

    /**
     * Map the successful value.
     */
    inline fun <U> map(transform: (T) -> U): Result<U> = when (this) {
        is Success -> Success(transform(data))
        is Error -> Error(exception)
    }

    /**
     * Flat map the successful value.
     */
    inline fun <U> flatMap(transform: (T) -> Result<U>): Result<U> = when (this) {
        is Success -> transform(data)
        is Error -> Error(exception)
    }

    /**
     * Get the value or throw the error.
     */
    fun getOrThrow(): T = when (this) {
        is Success -> data
        is Error -> throw exception
    }

    /**
     * Get the value or null.
     */
    fun getOrNull(): T? = when (this) {
        is Success -> data
        is Error -> null
    }

    /**
     * Get the error or null.
     */
    fun errorOrNull(): Throwable? = when (this) {
        is Success -> null
        is Error -> exception
    }

    /**
     * Check if this is a success.
     */
    fun isSuccess(): Boolean = this is Success

    /**
     * Check if this is an error.
     */
    fun isError(): Boolean = this is Error
}
