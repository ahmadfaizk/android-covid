package com.faiz.covid19.model

data class World(
    val updated: Long,
    val cases: Long,
    val todayCases: Long,
    val deaths: Long,
    val totalDeaths: Long,
    val recovered: Long,
    val active: Long,
    val critical: Long,
    val casesPerOneMillion: Long,
    val tests: Long,
    val testsPerOneMillion: Long,
    val affectedCountries: Long
)