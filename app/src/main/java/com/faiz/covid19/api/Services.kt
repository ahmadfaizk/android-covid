package com.faiz.covid19.api

import com.faiz.covid19.model.NationalReponse
import com.faiz.covid19.model.ProvinceResponse
import retrofit2.Call
import retrofit2.http.GET

interface Services {
    @GET("COVID19_Indonesia_per_Provinsi/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json")
    fun getProvinceStatistic(): Call<ProvinceResponse>

    @GET("Statistik_Perkembangan_COVID19_Indonesia/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json")
    fun getNationalStatistic(): Call<NationalReponse>
}