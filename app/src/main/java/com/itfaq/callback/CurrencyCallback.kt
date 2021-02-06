package com.itfaq.callback

import com.itfaq.model.api.CurrencyRates

interface CurrencyCallback {
    fun onDataSuccess(model: List<CurrencyRates>)

    fun onDataFailed()
}