package org.sanjaydraws.profanityfilter.core.domain



sealed class ResultResponse<out T> {
    data class Success<T>(val data: T) : ResultResponse<T>()
    data class Failure(val error: Throwable) : ResultResponse<Nothing>()

    fun getOrNull(): T? = when (this) {
        is Success -> data
        is Failure -> null
        else -> null
    }

    fun exceptionOrNull(): Throwable? = when (this) {
        is Failure -> error
        is Success -> null
        else -> null

    }

    inline fun onFailure(action: (Throwable) -> Unit): ResultResponse<T> {
        if (this is Failure) action(error)
        return this
    }
}
