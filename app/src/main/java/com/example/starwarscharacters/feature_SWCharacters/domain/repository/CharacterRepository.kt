package com.example.starwarscharacters.feature_SWCharacters.domain.repository

import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
	fun getAllCharacters(): Flow<List<Character>>
	suspend fun syncCharacters()
//	fun getCharacter(): Flow<Character>
//	suspend fun refreshCharacters(): Result<Unit>
//	suspend fun refreshCharacter(characterId: Long): Result<Unit>
}