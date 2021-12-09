package com.mariomanzano.marvelcompose.data.network.model

data class ApiEvent(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: ApiThumbnail,
    val characters: ApiReferenceList,
    val comics: ApiReferenceList,
    val series: ApiReferenceList,
    val stories: ApiReferenceList,
    val urls: List<ApiUrl>,
    val start: String,
    val end: String,
    val previous: ApiReference,
    val next: ApiReference,
    val modified: String,
    val resourceURI: String
)