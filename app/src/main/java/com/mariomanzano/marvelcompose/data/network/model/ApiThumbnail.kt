package com.mariomanzano.marvelcompose.data.network.model

data class ApiThumbnail(
    val extension: String,
    val path: String
)

fun ApiThumbnail.asString() = "$path.$extension".replace("http", "https")