package com.kkonradsoftware.libraries.network.interceptor

import com.kkonradsoftware.libraries.network.BuildConfig
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class AuthenticationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return request.newBuilder()
            .headers(
                headers = Headers.Builder()
                    .add(name = AUTHORIZATION_HEADER, value = "Bearer ${BuildConfig.API_KEY}")
                    .build()
            )
            .build()
            .let(chain::proceed)
    }
}

private const val AUTHORIZATION_HEADER = "Authorization"