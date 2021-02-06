package com.itfaq.main

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.itfaq.adapter.ListAdapter
import com.itfaq.callback.CurrencyCallback
import com.itfaq.main.databinding.ActivityMainBinding
import com.itfaq.model.api.CurrencyRates
import com.kotlindemo.model.CurrencyViewModel
import java.util.*
import javax.security.auth.login.LoginException


class MainActivity : AppCompatActivity(), CurrencyCallback {

    private lateinit var currencyViewModel: CurrencyViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ListAdapter
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initObject()
        QuestionAnswer()
    }


    private fun initObject() {
        currencyViewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)
        currencyViewModel.setInterface(this)
        currencyViewModel.getCurrencyRates()

        adapter = ListAdapter(arrayListOf())
        binding.rvCurrency.adapter = adapter

        Glide.with(this).load("https://www.countryflags.io/eu/flat/64.png").into(binding.imgBase)
    }

    override fun onDataSuccess(list: List<CurrencyRates>) {
        Log.e(TAG, "onDataSuccess: ")
        adapter.updateData(list)
    }

    override fun onDataFailed() {
        Log.e(TAG, "onDataFailed: ")
        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
    }

    fun clickOnItem(currencyRates: CurrencyRates) {
        Log.e(TAG, "clickOnItem: " + currencyRates.currencyRate)
        val dialog: Dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_currency)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        Glide.with(this).load("https://www.countryflags.io/eu/flat/64.png")
            .into(dialog.findViewById<ImageView>(R.id.imgFlag))

        val url = "https://www.countryflags.io/" + currencyRates.currencyName?.subSequence(
            0,
            2
        ) + "/flat/64.png"
        Glide.with(this).load(url).apply(RequestOptions().placeholder(R.drawable.ic_flag))
            .into(dialog.findViewById<ImageView>(R.id.imgFlag2))

        dialog.findViewById<ImageView>(R.id.imgCancel)?.setOnClickListener {
            dialog.dismiss()
        }
        val etPrice: EditText? = dialog.findViewById<EditText>(R.id.etPrice)
        etPrice?.setSelection(etPrice.text.length)
        etPrice?.addTextChangedListener {
            Log.e(TAG, "clickOnItem: " + it.toString())
            if (it.toString().length > 0) {
                val value: Double = it.toString().toDouble()
                val result: Double = value * currencyRates.currencyRate!!
                dialog.findViewById<TextView>(R.id.tvPrice2)?.text = String.format("%.2f", result)
            } else {
                dialog.findViewById<TextView>(R.id.tvPrice2)?.text =
                    String.format("%.2f", currencyRates.currencyRate)
            }
        }
        dialog.findViewById<TextView>(R.id.tvTitle2)?.text = (currencyRates.currencyName)
        dialog.findViewById<TextView>(R.id.tvPrice2)?.text =
            String.format("%.2f", currencyRates.currencyRate)
    }
}