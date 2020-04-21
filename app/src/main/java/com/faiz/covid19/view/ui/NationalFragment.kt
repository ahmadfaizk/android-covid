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
import com.faiz.covid19.model.National
import com.faiz.covid19.model.NationalReponse
import com.faiz.covid19.view.adapter.NationalAdapter
import kotlinx.android.synthetic.main.fragment_national.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class NationalFragment : Fragment() {

    private var listNational = ArrayList<National>()
    private var nationalAdapter = NationalAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_national, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvNational.layoutManager = LinearLayoutManager(context)
        rvNational.adapter = nationalAdapter
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        rvNational.addItemDecoration(dividerItemDecoration)

        requestData()
        showLoading(true)
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
                    }
                    showMessage("Get ${listNational.size} Data Statistic")
                    nationalAdapter.setData(listNational)
                }
            }

            override fun onFailure(call: Call<NationalReponse>, t: Throwable) {
                showLoading(false)
                t.printStackTrace()
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
