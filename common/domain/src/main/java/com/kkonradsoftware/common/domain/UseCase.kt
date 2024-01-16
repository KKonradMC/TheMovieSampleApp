package com.kkonradsoftware.common.domain

interface UseCase<PARAMS, RESULT> {
    suspend operator fun invoke(params: PARAMS): RESULT
}