package com.example.starwarscharacters.feature_SWCharacters.data.remote.api

import com.example.starwarscharacters.feature_SWCharacters.data.remote.dto.CharacterDto
import com.example.starwarscharacters.feature_SWCharacters.data.remote.dto.FilmItemDto
import com.example.starwarscharacters.feature_SWCharacters.data.remote.dto.PlanetDto
import com.example.starwarscharacters.feature_SWCharacters.data.remote.dto.SpeciesDto
import retrofit2.http.GET

interface AppApi {

	@GET("people/")
	suspend fun getAllCharacters(): List<CharacterDto>

	@GET("species/")
	suspend fun getSpecies(): List<SpeciesDto>

	@GET("films/")
	suspend fun getFilmItem(): List<FilmItemDto>

	@GET("planets/")
	suspend fun getPlanets(): List<PlanetDto>
}