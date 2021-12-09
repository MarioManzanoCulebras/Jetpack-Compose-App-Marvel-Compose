package com.mariomanzano.marvelcompose.data.repositories

import com.mariomanzano.marvelcompose.data.model.*
import com.mariomanzano.marvelcompose.data.model.ReferenceList.*
import com.mariomanzano.marvelcompose.data.model.ReferenceList.Type.*
import com.mariomanzano.marvelcompose.data.network.model.*

fun ApiCharacter.asCharacter(): Character = Character(
    id,
    name,
    description,
    thumbnail.asString(),
    listOf(
        comics.toDomain(COMIC),
        events.toDomain(EVENT),
        series.toDomain(SERIES),
        stories.toDomain(STORY)
    ),
    urls.map { Url(it.type, it.url) }
)

fun ApiEvent.asEvent(): Event = Event(
    id,
    title,
    description,
    thumbnail.asString(),
    listOf(
        comics.toDomain(COMIC),
        characters.toDomain(CHARACTER),
        series.toDomain(SERIES),
        stories.toDomain(STORY)
    ),
    urls.map { Url(it.type, it.url) }
)

fun ApiComic.asComic(): Comic = Comic(
    id,
    title,
    description ?: "",
    thumbnail.asString(),
    format.toDomain(),
    listOf(
        characters.toDomain(CHARACTER),
        events.toDomain(EVENT),
        series.toDomain(SERIES),
        stories.toDomain(STORY)
    ),
    urls.map { Url(it.type, it.url) }
)

private fun String.toDomain(): Comic.Format = when (this) {
    "magazine" -> Comic.Format.MAGAZINE
    "trade paperback" -> Comic.Format.TRADE_PAPERBACK
    "hardcover" -> Comic.Format.HARDCOVER
    "digest" -> Comic.Format.DIGEST
    "graphic novel" -> Comic.Format.GRAPHIC_NOVEL
    "digital comic" -> Comic.Format.DIGITAL_COMIC
    "infinite comic" -> Comic.Format.INFINITE_COMIC
    else -> Comic.Format.COMIC
}

fun Comic.Format.toStringFormat(): String = when (this) {
    Comic.Format.COMIC -> "comic"
    Comic.Format.MAGAZINE -> "magazine"
    Comic.Format.TRADE_PAPERBACK -> "trade paperback"
    Comic.Format.HARDCOVER -> "hardcover"
    Comic.Format.DIGEST -> "digest"
    Comic.Format.GRAPHIC_NOVEL -> "graphic novel"
    Comic.Format.DIGITAL_COMIC -> "digital comic"
    Comic.Format.INFINITE_COMIC -> "infinite comic"
}

private fun ApiReferenceList.toDomain(type: Type): ReferenceList {
    return ReferenceList(
        type,
        items
            ?.let { items.map { Reference(it.name) } }
            ?: emptyList()
    )
}