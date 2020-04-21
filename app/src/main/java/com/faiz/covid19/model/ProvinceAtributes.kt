package com.faiz.covid19.model

import com.google.gson.annotations.SerializedName

data class ProvinceAtributes(
    @SerializedName("FID")
    var fid: Int,
    @SerializedName("Kode_Provi")
    var code: Int,
    @SerializedName("Provinsi")
    var name: String,
    @SerializedName("Kasus_Posi")
    var positive: Int,
    @SerializedName("Kasus_Semb")
    var cured: Int,
    @SerializedName("Kasus_Meni")
    var died: Int
)