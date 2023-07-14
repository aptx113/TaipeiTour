package com.dante.taipeitour.di

import com.dante.taipeitour.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    @Singleton
    @Provides
    fun providesOkHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                },
        )
        .build()
}
