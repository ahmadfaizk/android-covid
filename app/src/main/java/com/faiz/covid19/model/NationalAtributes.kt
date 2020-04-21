package com.faiz.covid19.model

import com.google.gson.annotations.SerializedName

data class NationalAtributes(
    @SerializedName("Hari_ke")
    var daysTo: Int,
    @SerializedName("Tanggal")
    var date: Long,
    @SerializedName("Jumlah_Kasus_Baru_per_Hari")
    var newCasesDaily: Int? = null,
    @SerializedName("Jumlah_Kasus_Kumulatif")
    var newCasesCumulative: Int? = null,
    @SerializedName("Jumlah_pasien_dalam_perawatan")
    var patientCare: Int? = null,
    @SerializedName("Persentase_Pasien_dalam_Perawatan")
    var percentagePatientCare: Float? = null,
    @SerializedName("Jumlah_Pasien_Sembuh")
    var patientCured: Int? = null,
    @SerializedName("Persentase_Pasien_Sembuh")
    var percentagePatientCured: Float? = null,
    @SerializedName("Jumlah_Pasien_Meninggal")
    var patientDied: Int? = null,
    @SerializedName("Persentase_Pasien_Meninggal")
    var percentagePatientDied: Float? = null,
    @SerializedName("Jumlah_Kasus_Dirawat_per_Hari")
    var patientCareDaily: Int? = null,
    @SerializedName("Jumlah_Kasus_Sembuh_per_Hari")
    var patientCuredDaily: Int? = null,
    @SerializedName("Jumlah_Kasus_Meninggal_per_Hari")
    var patientDiedDaily: Int? = null,
    @SerializedName("FID")
    var fid: Int
)