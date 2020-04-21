package com.faiz.covid19.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faiz.covid19.R
import com.faiz.covid19.model.Province
import kotlinx.android.synthetic.main.item_province.view.*

class ProvinceAdapter : RecyclerView.Adapter<ProvinceAdapter.ViewHolder>() {

    private val listProvince = ArrayList<Province>()

    fun setProvince(listProvince: ArrayList<Province>) {
        if (this.listProvince.isNotEmpty()) {
            this.listProvince.clear()
        }
        this.listProvince.addAll(listProvince)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_province, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listProvince.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listProvince[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(province: Province) {
            with(itemView) {
                tvName.text = province.attributes.name
                tvPositive.text = province.attributes.positive.toString()
                tvCured.text = province.attributes.cured.toString()
                tvDied.text = province.attributes.died.toString()
            }
        }
    }
}