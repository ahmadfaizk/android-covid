package com.faiz.covid19.model

data class NationalReponse(
    var objectIdFieldName: String,
    var uniqueIdFieldName: UniqueIdFieldName,
    var globalIdFieldName: String,
    var fields: ArrayList<Field>,
    var features: ArrayList<National>
)