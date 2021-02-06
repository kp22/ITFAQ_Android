package com.itfaq.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class CurrencyModel {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("timestamp")
    @Expose
    var timestamp: Int? = null

    @SerializedName("base")
    @Expose
    var base: String? = null

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("rates")
    @Expose
    var rates: HashMap<String, Double>? = null
}