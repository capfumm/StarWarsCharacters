package com.example.starwarscharacters.feature_SWCharacters.data.repository

import com.example.starwarscharacters.feature_SWCharacters.data.dao.CharactersDao
import com.example.starwarscharacters.feature_SWCharacters.data.mappers.toDomain
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character
import com.example.starwarscharacters.feature_SWCharacters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl(
	private val dao: CharactersDao
): CharacterRepository {
	override fun getAllCharacters(): Flow<List<Character>> {
		return dao.getAllCharacters().map { characterEntities ->
			characterEntities.map { it.toDomain() }
		}
	}

	override suspend fun syncCharacters() {
		val entities = api.getAllCharacters()
			.map { it.toEntity() }
		dao.upsertAll(entities)
	}
}