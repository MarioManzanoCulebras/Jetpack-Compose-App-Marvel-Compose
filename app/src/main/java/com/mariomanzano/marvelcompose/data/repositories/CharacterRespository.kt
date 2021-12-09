package com.mariomanzano.marvelcompose.data.repositories

import com.mariomanzano.marvelcompose.data.network.ApiClient

import com.mariomanzano.marvelcompose.data.model.Character

object CharactersRepository : Repository<Character>() {

    suspend fun get(): List<Character> = super.get {
        ApiClient
            .charactersService
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }

    suspend fun find(id: Int): Character = super.find(
        id,
        findActionRemote = {
            ApiClient
                .charactersService
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )
}