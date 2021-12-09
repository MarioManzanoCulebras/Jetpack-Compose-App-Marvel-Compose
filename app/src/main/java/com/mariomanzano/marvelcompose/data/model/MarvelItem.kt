package com.mariomanzano.marvelcompose.data.model

interface MarvelItem {
    val id: Int
    val title: String
    val description: String
    val thumbnail: String
    val references: List<ReferenceList>
    val urls: List<Url>
}