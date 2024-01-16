package com.kkonradsoftware.common.domain.model

sealed interface DomainError {
    sealed interface Network : DomainError {
        data class Http(
            val code: Int,
            val message: String,
        ) : Network
    }


    data class Standard(
        val throwable: Throwable,
    ) : DomainError

    object InvalidData : DomainError
}
