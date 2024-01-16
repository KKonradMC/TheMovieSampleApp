package com.kkonradsoftware.libraries.network

import com.kkonradsoftware.common.domain.model.DomainError
import com.kkonradsoftware.common.domain.model.Empty
import com.kkonradsoftware.common.domain.model.Failure
import com.kkonradsoftware.common.domain.model.NetworkResult
import com.kkonradsoftware.common.domain.model.Success
import retrofit2.Response
import retrofit2.Retrofit

interface NetworkConnectionSupporter {

    fun <T> createClient(clazz: Class<T>): T

    suspend fun <DATA> call(
        networkCallback: suspend () -> Response<DATA>
    ): NetworkResult<DomainError, DATA>
}

internal class NetworkConnectionSupporterImpl(
    private val retrofit: Retrofit,
) : NetworkConnectionSupporter {

    override fun <T> createClient(clazz: Class<T>): T = retrofit.create(clazz)

    override suspend fun <DATA> call(
        networkCallback: suspend () -> Response<DATA>
    ): NetworkResult<DomainError, DATA> = try {
        val response = networkCallback()
        if (response.isSuccessful) {
            response.body()?.let { Success(value = it) } ?: Empty
        } else {
            Failure(
                value = DomainError.Network.Http(
                    code = response.code(),
                    message = response.message(),
                )
            )
        }
    } catch (e: Exception) {
        Failure(DomainError.Standard(throwable = e))
    }
}
