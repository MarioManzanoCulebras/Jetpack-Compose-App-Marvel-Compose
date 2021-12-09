package com.mariomanzano.marvelcompose.data.network.model

data class ApiSeries(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiSerie>,
    val returned: Int
)