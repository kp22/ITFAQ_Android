package com.itfaq.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.itfaq.main.MainActivity
import com.itfaq.main.R
import com.itfaq.main.databinding.ItemListBinding
import com.itfaq.model.api.CurrencyRates

class ListAdapter(var list: List<CurrencyRates>) : RecyclerView.Adapter<ListAdapter.Viewholder>() {
    lateinit var context: Context
    val TAG = ListAdapter::class.java.simpleName

    class Viewholder(val bind: ItemListBinding) : RecyclerView.ViewHolder(bind.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        context = parent.context
        val binding: ItemListBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_list, parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind.tvTitle.text = list[position].currencyName
        list[position].currencyRate.let {
            val rate: Float = it!!.toFloat()
            holder.bind.tvPrice.text = String.format("%.2f",rate)
        }
        val url ="https://www.countryflags.io/"+list[position].currencyName?.subSequence(0,2) +"/flat/64.png"
        Glide.with(context).load(url).apply(RequestOptions().placeholder(R.drawable.ic_flag)).into(holder.bind.imgFlag)

        holder.bind.root.setOnClickListener {
            Log.e(TAG, "onBindViewHolder: pos $position" )
            (context as MainActivity).clickOnItem(list[position])
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(list: List<CurrencyRates>) {
        this.list = list
        notifyDataSetChanged()
    }

}