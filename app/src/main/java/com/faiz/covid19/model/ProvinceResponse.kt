package com.faiz.covid19.model

data class ProvinceResponse(
    var objectIdFieldName: String,
    var uniqueIdFieldName: UniqueIdFieldName,
    var globalIdFieldName: String,
    var fields: ArrayList<Field>,
    var features: ArrayList<Province>
)