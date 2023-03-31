package com.appinsnap.lwmc.dataclasses.responsehandler

sealed class Either<T> {
    data class Success<T>(val data: T) : Either<T>()
    data class Error<T>(val message: String,val errorCode: Int) : Either<T>()
    companion object {
        fun <T> success(data: T) = Success(data)
        fun <T> error(message: String, code: Int) = Error<T>(message ,code)
    }
    inline fun onSuccess(block: (T) -> Unit) : Either<T> = apply {
        if (this is Success) {
            block(data)
        }
    }
    inline fun onFailure(block: (ErrorWrapper) -> Unit) : Either<T> = apply {
        if (this is Error) {
            block(ErrorWrapper(message,errorCode))
        }
    }
}
