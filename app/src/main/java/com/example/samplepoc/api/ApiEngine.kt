package com.example.samplepoc.api

import com.example.samplepoc.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiEngine {

    private val retrofitClient: Retrofit.Builder by lazy {
        val levelType: HttpLoggingInterceptor.Level
        if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            levelType = HttpLoggingInterceptor.Level.BODY else levelType =
            HttpLoggingInterceptor.Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.level = levelType

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)
            .connectTimeout(5, TimeUnit.SECONDS) // connect timeout
            .writeTimeout(5, TimeUnit.SECONDS) // write timeout
            .readTimeout(5, TimeUnit.SECONDS) // read timeout

        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }
}