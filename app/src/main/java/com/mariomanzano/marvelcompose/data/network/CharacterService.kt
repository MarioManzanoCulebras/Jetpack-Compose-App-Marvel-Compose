package com.mariomanzano.marvelcompose.data.network

import com.mariomanzano.marvelcompose.data.network.model.ApiCharacter
import com.mariomanzano.marvelcompose.data.network.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<ApiCharacter>

    @GET("/v1/public/characters/{characterId}")
    suspend fun findCharacter(
        @Path("characterId") characterId: Int,
    ): ApiResponse<ApiCharacter>
}