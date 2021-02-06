package com.kotlindemo.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.itfaq.callback.CurrencyCallback
import com.itfaq.main.BuildConfig
import com.itfaq.model.api.CurrencyModel
import com.itfaq.model.api.CurrencyRates
import com.kotlindemo.api.RetroClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var callback: CurrencyCallback

    fun setInterface(callback: CurrencyCallback) {
        this.callback = callback
    }

    fun getCurrencyRates() = viewModelScope.launch(Dispatchers.IO) {

        RetroClient.apiService.getCurrencyRates(BuildConfig.API_KEY)
            .enqueue(object : Callback<CurrencyModel> {
                override fun onResponse(
                    call: Call<CurrencyModel>,
                    response: Response<CurrencyModel>
                ) {
                    if (response.isSuccessful) {
                        val res: CurrencyModel? = response.body()
                        res?.rates.let {
                            val arrData = mutableListOf<CurrencyRates>()
                            for ((key, value) in it!!) {
                                arrData.add(CurrencyRates(key, value))
                            }
                            callback.onDataSuccess(arrData)
                        }

                    }
                }

                override fun onFailure(call: Call<CurrencyModel>, t: Throwable) {
                    Log.e("TAG", "onFailure: error - " + t.message)
                    callback.onDataFailed()
                }
            })
    }


}