package com.kkonradsoftware.libraries.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kkonradsoftware.libraries.network.BuildConfig
import com.kkonradsoftware.libraries.network.NetworkConnectionSupporter
import com.kkonradsoftware.libraries.network.NetworkConnectionSupporterImpl
import com.kkonradsoftware.libraries.network.SERVER_READ_TIMEOUT
import com.kkonradsoftware.libraries.network.SERVER_REQUEST_TIMEOUT
import com.kkonradsoftware.libraries.network.interceptor.AuthenticationInterceptor
import com.kkonradsoftware.libraries.network.logger.ApiLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    internal fun provideOkHttpBuilder(
        authenticationInterceptor: AuthenticationInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient.Builder = OkHttpClient.Builder()
        .callTimeout(timeout = SERVER_REQUEST_TIMEOUT, unit = TimeUnit.SECONDS)
        .readTimeout(timeout = SERVER_READ_TIMEOUT, unit = TimeUnit.SECONDS)
        .addInterceptor(interceptor = authenticationInterceptor)
        .addInterceptor(interceptor = loggingInterceptor)

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor(logger = ApiLogger).apply {
        setLevel(level = HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient = builder.build()

    @Provides
    fun provideGsonBuilder(): GsonBuilder = GsonBuilder()

    @Provides
    fun provideGson(gsonBuilder: GsonBuilder): Gson = gsonBuilder.create()

    @Provides
    @GsonConverterFactoryQualifier
    fun provideConverterFactory(gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)

    @Provides
    fun provideRetrofitBuilder(
        client: OkHttpClient,
        @GsonConverterFactoryQualifier converterFactory: Converter.Factory
    ): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BuildConfig.HOST)
        .client(client)
        .addConverterFactory(converterFactory)


    @Provides
    fun providesRetrofit(builder: Retrofit.Builder): Retrofit = builder.build()

    @Singleton
    @Provides
    fun providesNetworkConnectionSupporter(retrofit: Retrofit): NetworkConnectionSupporter =
        NetworkConnectionSupporterImpl(retrofit = retrofit)
}