package com.seekasia.jobseeker.data.data_resource.remote.api_manager

import android.content.Context
import com.seekasia.jobseeker.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApiManager {
    fun provide(context: Context, baseUrl: String): Retrofit {
        val client = OkHttpClient.Builder()
            .setHandleErrorsInterceptor()
            .setNetworkInterceptor(context = context)
            .setLoggerInterceptor(HttpLoggingInterceptor.Level.BODY)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(client)
            .setConverters()
            .baseUrl(baseUrl)
            .build()
    }

    private fun Retrofit.Builder.setConverters() = addConverterFactory(GsonConverterFactory.create())

    private fun OkHttpClient.Builder.setLoggerInterceptor(
        logLevel: HttpLoggingInterceptor.Level
    ): OkHttpClient.Builder {
        if(BuildConfig.DEBUG) addInterceptor(HttpLoggingInterceptor().apply {
            level = logLevel
        })

        return this
    }

    private fun OkHttpClient.Builder.setNetworkInterceptor(context: Context) : OkHttpClient.Builder =
        addInterceptor(NetworkConnectionInterceptor(context))

    private fun OkHttpClient.Builder.setHandleErrorsInterceptor() : OkHttpClient.Builder =
        addInterceptor(HandleErrorInterceptor())
}