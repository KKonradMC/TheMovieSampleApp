package com.kkonradsoftware.common.domain

import com.kkonradsoftware.common.domain.model.DomainError
import com.kkonradsoftware.common.domain.model.DomainResult

interface DomainResultUseCase<PARAMS, RESULT> : UseCase<PARAMS, DomainResult<DomainError, RESULT>>