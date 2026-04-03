package com.example.starwarscharacters.feature_SWCharacters.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterEntity
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

	@Query("SELECT * FROM characters")
	fun getAllCharacters(): Flow<List<CharacterEntity>>

	@Upsert
	suspend fun upsertAll(characters: List<CharacterEntity>)
}