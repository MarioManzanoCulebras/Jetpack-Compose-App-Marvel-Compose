package com.mariomanzano.marvelcompose.data.network.model

data class ApiEvents(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiEvent>,
    val returned: Int
)