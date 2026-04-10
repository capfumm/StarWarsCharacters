package com.example.starwarscharacters.feature_SWCharacters.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterEntity
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterFilmCrossRef
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterSpeciesCrossRef
import com.example.starwarscharacters.feature_SWCharacters.data.entities.FilmItemEntity
import com.example.starwarscharacters.feature_SWCharacters.data.entities.PlanetEntity
import com.example.starwarscharacters.feature_SWCharacters.data.entities.SpeciesEntity
import com.example.starwarscharacters.feature_SWCharacters.data.relation.CharacterWithDetails
import com.example.starwarscharacters.feature_SWCharacters.data.relation.SpeciesWithPlanets
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

	@Query("SELECT * FROM characters")
	fun getAllCharacters(): Flow<List<CharacterEntity>>

	@Query("SELECT * FROM species WHERE id = :id")
	fun getSpeciesByCharacterId(id: Long): Flow<List<SpeciesEntity>>

	@Query("SELECT * FROM filmItems WHERE id = :id ORDER BY episodeId")
	fun getFilmItemsById(id: Long): Flow<List<FilmItemEntity>>

	@Query("SELECT * FROM characters WHERE id = :id")
	fun getCharacterById(id: Long): Flow<CharacterEntity>
	@Upsert
	suspend fun upsertCharacters(entities: List<CharacterEntity>)

	@Upsert
	suspend fun upsertSpecies(entities: List<SpeciesEntity>)

	@Upsert
	suspend fun upsertFilms(entities: List<FilmItemEntity>)

	@Upsert
	suspend fun upsertPlanets(entities: List<PlanetEntity>)

	@Transaction
	@Query("SELECT * FROM characters WHERE id = :characterId")
	fun getFullCharacterInfo(characterId: Long): Flow<CharacterWithDetails?>

	@Transaction
	@Query("SELECT * FROM species WHERE homeworldId = :planetId")
	fun getSpeciesWithPlanet(planetId: Long): Flow<SpeciesWithPlanets?>

	@Upsert
	suspend fun upsertCharacterFilmRefs(refs: List<CharacterFilmCrossRef>)
	@Upsert
	suspend fun upsertCharacterSpeciesRefs(species: List<CharacterSpeciesCrossRef>)

}