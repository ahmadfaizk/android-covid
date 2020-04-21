package com.faiz.covid19.view.ui

import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.faiz.covid19.R
import com.faiz.covid19.api.ApiClient
import com.faiz.covid19.model.National
import com.faiz.covid19.model.NationalReponse
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_chart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class ChartActivity : AppCompatActivity(), OnChartValueSelectedListener {

    private val listNational = ArrayList<National>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        requestData()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        pieChart.setOnChartValueSelectedListener(this)
    }

    private fun requestData() {
        val request = ApiClient.retrofit.getNationalStatistic()
        request.enqueue(object : Callback<NationalReponse> {
            override fun onResponse(call: Call<NationalReponse>, response: Response<NationalReponse>) {
                showLoading(false)
                val nationals = response.body()?.features
                if (nationals != null) {
                    for (national in nationals) {
                        if (national.attributes.newCasesDaily != null) {
                            listNational.add(national)
                        } else {
                            break
                        }
                        setPieChart()
                        setLineChart()
                        setLineChart2()
                    }
                    showMessage("Get ${listNational.size} Data Statistic")
                }
            }

            override fun onFailure(call: Call<NationalReponse>, t: Throwable) {
                showLoading(false)
                t.printStackTrace()
                showMessage(t.message.toString())
            }
        })
    }

    private fun setPieChart() {
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.description.isEnabled = false
        val legend = pieChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.isEnabled = false

        var cases = listNational[listNational.lastIndex].attributes
        tvNewCasesCumulative.text = "Total ${cases.newCasesCumulative} Kasus Covid-19"
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(cases.patientCare!!.toFloat(), "Dirawat"))
        entries.add(PieEntry(cases.patientCured!!.toFloat(), "Sembuh"))
        entries.add(PieEntry(cases.patientDied!!.toFloat(), "Meninggal"))

        val pieDataSet = PieDataSet(entries, "Pasien Covid-19")
        pieDataSet.colors = ColorTemplate.createColors(ColorTemplate.MATERIAL_COLORS)
        //pieDataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        pieDataSet.xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        pieDataSet.sliceSpace = 3f
        pieDataSet.selectionShift = 5f
        val data = PieData(pieDataSet)
        data.setValueTextColor(Color.BLACK)

        pieChart.data = data
        pieChart.animateXY(3000, 3000)
    }

    private fun setLineChart() {
        lineChart.description.isEnabled = false
        //lineChart.data.isHighlightEnabled = false
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        val casesEntry = ArrayList<Entry>()
        val curedEntry = ArrayList<Entry>()
        val diedEntry = ArrayList<Entry>()
        val listDate = ArrayList<String>()
        var index = 0F

        for (national in listNational) {
            casesEntry.add(Entry(index, national.attributes.newCasesDaily!!.toFloat()))
            curedEntry.add(Entry(index, national.attributes.patientCuredDaily!!.toFloat()))
            diedEntry.add(Entry(index, national.attributes.patientDiedDaily!!.toFloat()))
            listDate.add(getDate(national.attributes.date))
            index++
        }
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return listDate[value.toInt()]
            }
        }

        val lineDataSetCases = LineDataSet(casesEntry, "Kasus Positif")
        lineDataSetCases.setDrawCircles(false)
        lineDataSetCases.setDrawValues(false)
        lineDataSetCases.color = Color.BLUE
        lineDataSetCases.mode = LineDataSet.Mode.CUBIC_BEZIER
        val lineDataSetCured = LineDataSet(curedEntry, "Kasus Sembuh")
        lineDataSetCured.setDrawCircles(false)
        lineDataSetCured.setDrawValues(false)
        lineDataSetCured.color = Color.GREEN
        lineDataSetCured.mode = LineDataSet.Mode.CUBIC_BEZIER
        val lineDataSetDied = LineDataSet(diedEntry, "Kasus Meninggal")
        lineDataSetDied.setDrawCircles(false)
        lineDataSetDied.setDrawValues(false)
        lineDataSetDied.color = Color.RED
        lineDataSetDied.mode = LineDataSet.Mode.CUBIC_BEZIER

        val lineData = LineData(lineDataSetCases, lineDataSetCured, lineDataSetDied)
        lineChart.data = lineData
        lineChart.animateXY(3000, 3000)
    }

    private fun setLineChart2() {
        lineChart2.description.isEnabled = false
        //lineChart.data.isHighlightEnabled = false
        val xAxis = lineChart2.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        val casesEntry = ArrayList<Entry>()
        val curedEntry = ArrayList<Entry>()
        val diedEntry = ArrayList<Entry>()
        val listDate = ArrayList<String>()
        var index = 0F

        for (national in listNational) {
            casesEntry.add(Entry(index, national.attributes.newCasesCumulative!!.toFloat()))
            curedEntry.add(Entry(index, national.attributes.patientCured!!.toFloat()))
            diedEntry.add(Entry(index, national.attributes.patientDied!!.toFloat()))
            listDate.add(getDate(national.attributes.date))
            index++
        }
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return listDate[value.toInt()]
            }
        }

        val lineDataSetCases = LineDataSet(casesEntry, "Kasus Positif")
        lineDataSetCases.setDrawCircles(false)
        lineDataSetCases.setDrawValues(false)
        lineDataSetCases.color = Color.BLUE
        lineDataSetCases.mode = LineDataSet.Mode.CUBIC_BEZIER
        val lineDataSetCured = LineDataSet(curedEntry, "Kasus Sembuh")
        lineDataSetCured.setDrawCircles(false)
        lineDataSetCured.setDrawValues(false)
        lineDataSetCured.color = Color.GREEN
        lineDataSetCured.mode = LineDataSet.Mode.CUBIC_BEZIER
        val lineDataSetDied = LineDataSet(diedEntry, "Kasus Meninggal")
        lineDataSetDied.setDrawCircles(false)
        lineDataSetDied.setDrawValues(false)
        lineDataSetDied.color = Color.RED
        lineDataSetDied.mode = LineDataSet.Mode.CUBIC_BEZIER

        val lineData = LineData(lineDataSetCases, lineDataSetCured, lineDataSetDied)
        lineChart2.data = lineData
        lineChart2.animateXY(3000, 3000)
    }

    private fun getDate(timestamp: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        return DateFormat.format("dd MMM", calendar).toString()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(state: Boolean) {

    }

    override fun onNothingSelected() {

    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {

    }
}
