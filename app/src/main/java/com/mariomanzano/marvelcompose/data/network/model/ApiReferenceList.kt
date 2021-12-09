package com.mariomanzano.marvelcompose.data.network.model

data class ApiReferenceList(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiReference>?,
    val returned: Int
)