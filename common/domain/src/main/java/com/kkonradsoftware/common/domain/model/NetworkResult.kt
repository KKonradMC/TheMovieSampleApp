package com.kkonradsoftware.common.domain.model

sealed interface DomainResult<out FAILURE, out SUCCESS> {
    fun <RESULT> execute(
        onSuccess: (SUCCESS) -> RESULT,
        onFailure: (FAILURE) -> RESULT,
    ): RESULT = when (this) {
        is Success -> onSuccess(value)
        is Failure -> onFailure(value)
    }

    suspend fun <RESULT> suspendExecute(
        onSuccess: suspend (SUCCESS) -> RESULT,
        onFailure: suspend (FAILURE) -> RESULT,
    ): RESULT = when (this) {
        is Success -> onSuccess(value)
        is Failure -> onFailure(value)
    }
}

sealed interface NetworkResult<out FAILURE, out SUCCESS> {
    fun <RESULT> execute(
        onSuccess: (SUCCESS) -> RESULT,
        onEmpty: () -> RESULT,
        onFailure: (FAILURE) -> RESULT,
    ): RESULT = when (this) {
        is Success -> onSuccess(value)
        is Failure -> onFailure(value)
        is Empty -> onEmpty()
    }
}


data class Success<SUCCESS>(val value: SUCCESS) :
    NetworkResult<Nothing, SUCCESS>, DomainResult<Nothing, SUCCESS>

data class Failure<FAILURE>(val value: FAILURE) :
    NetworkResult<FAILURE, Nothing>, DomainResult<FAILURE, Nothing>

object Empty : NetworkResult<Nothing, Nothing>
