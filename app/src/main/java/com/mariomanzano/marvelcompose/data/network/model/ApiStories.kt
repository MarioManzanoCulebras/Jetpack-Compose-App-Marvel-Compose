package com.mariomanzano.marvelcompose.data.network.model

data class ApiStories(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiStory>,
    val returned: Int
)