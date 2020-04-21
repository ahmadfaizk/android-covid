package com.faiz.covid19.view.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faiz.covid19.R
import com.faiz.covid19.model.National
import kotlinx.android.synthetic.main.item_national.view.*
import java.util.*
import kotlin.collections.ArrayList

class NationalAdapter : RecyclerView.Adapter<NationalAdapter.ViewHolder>() {

    private val listNational = ArrayList<National>()

    fun setData(listNational: ArrayList<National>) {
        if (this.listNational.isNotEmpty())
            this.listNational.clear()
        this.listNational.addAll(listNational)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_national, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listNational.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNational[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(national: National) {
            with(itemView) {
                tvDaysTo.text = national.attributes.daysTo.toString()
                tvDate.text = getDate(national.attributes.date)
                tvNewCasesDaily.text = national.attributes.newCasesDaily.toString()
                tvNewCasesCumulative.text = national.attributes.newCasesCumulative.toString()
                tvPatientCare.text = national.attributes.patientCare.toString()
                tvPatientCareDaily.text = national.attributes.patientCareDaily.toString()
                tvPatientCured.text = national.attributes.patientCured.toString()
                tvPatientCuredDaily.text = national.attributes.patientCuredDaily.toString()
                tvPatientDied.text = national.attributes.patientDied.toString()
                tvPatientDiedDaily.text = national.attributes.patientDiedDaily.toString()
                tvPercentagePatientCare.text = national.attributes.percentagePatientCare.toString()
                tvPercentagePatientCured.text = national.attributes.percentagePatientCured.toString()
                tvPercentagePatientDied.text = national.attributes.percentagePatientDied.toString()
            }
        }
    }

    private fun getDate(timestamp: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        return DateFormat.format("dd-MM-yyyy", calendar).toString()
    }
}