package com.mariomanzano.marvelcompose.data.network.model

data class ApiComics(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiComic>,
    val returned: Int
)