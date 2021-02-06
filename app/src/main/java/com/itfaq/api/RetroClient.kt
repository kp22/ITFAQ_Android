package com.kotlindemo.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetroClient {
    //  full URL for test:
    //  http://data.fixer.io/api/latest?access_key=1dee8c3d2dac4ef3048c6ac1199dbe34
    var BASE_URL = "http://data.fixer.io/api/"

    private val retrofitInstance: Retrofit
        private get() {
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()
        }

    /**
     * Get API Service
     * @return API Service
     */
    val apiService: ApiService get() = retrofitInstance.create(ApiService::class.java)

}