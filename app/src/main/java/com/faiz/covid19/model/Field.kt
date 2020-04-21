package com.faiz.covid19.model

data class Field(
    var name: String? = null,
    var type: String? = null,
    var alias: String? = null,
    var sqlType: String? = null,
    var domain: String? = null,
    var defaultValue: String? = null
)