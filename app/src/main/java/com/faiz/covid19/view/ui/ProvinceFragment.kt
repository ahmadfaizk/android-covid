package com.faiz.covid19.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.faiz.covid19.R
import com.faiz.covid19.api.ApiClient
import com.faiz.covid19.model.Province
import com.faiz.covid19.model.ProvinceResponse
import com.faiz.covid19.view.adapter.ProvinceAdapter
import kotlinx.android.synthetic.main.fragment_province.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ProvinceFragment : Fragment() {

    private val provinceAdapter = ProvinceAdapter()
    private var listProvince = ArrayList<Province>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_province, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvProvince.layoutManager = LinearLayoutManager(context)
        rvProvince.adapter = provinceAdapter
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        rvProvince.addItemDecoration(dividerItemDecoration)

        requestData()
        showLoading(true)
    }

    private fun requestData() {
        val request = ApiClient.retrofit.getProvinceStatistic()
        request.enqueue(object : Callback<ProvinceResponse> {
            override fun onResponse(call: Call<ProvinceResponse>, response: Response<ProvinceResponse>) {
                val provinces = response.body()?.features
                showLoading(false)
                if (provinces != null) {
                    listProvince = provinces
                    showMessage("Succes Get ${listProvince.size} Province Statistic")
                    provinceAdapter.setProvince(listProvince)
                } else {
                    showMessage("Data Kosong")
                }
            }

            override fun onFailure(call: Call<ProvinceResponse>, t: Throwable) {
                t.printStackTrace()
                showLoading(false)
                showMessage(t.message.toString())
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state)
            progressBar.visibility = View.VISIBLE
        else
            progressBar.visibility = View.GONE
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
