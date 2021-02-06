package com.kotlindemo.api

import com.itfaq.model.api.CurrencyModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("latest")
    fun getCurrencyRates(@Query("access_key") key:String) : Call<CurrencyModel>
}