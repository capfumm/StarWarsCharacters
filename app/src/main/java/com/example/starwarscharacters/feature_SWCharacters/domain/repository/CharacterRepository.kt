package com.example.starwarscharacters.feature_SWCharacters.domain.repository

import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character
import com.example.starwarscharacters.feature_SWCharacters.domain.model.CharacterDetailModel
import com.example.starwarscharacters.feature_SWCharacters.domain.model.SpeciesDetailModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
	fun getAllCharacters(): Flow<List<Character>>
	suspend fun syncCharacters()
	suspend fun syncSpecies()
	suspend fun syncFilmItems()
	suspend fun syncPlanets()
	fun getCharacterDetails(id: Long): Flow<CharacterDetailModel?>
	fun getSpeciesWithPlanet(id: Long): Flow<SpeciesDetailModel?>
}