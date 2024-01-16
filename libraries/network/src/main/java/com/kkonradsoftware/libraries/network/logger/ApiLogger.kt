package com.kkonradsoftware.libraries.network.logger

import android.util.Log
import com.kkonradsoftware.libraries.network.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

object ApiLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        if (BuildConfig.DEBUG) {
            Log.d("ApiLogger", message)
        }
    }
}
