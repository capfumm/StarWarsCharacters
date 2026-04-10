package com.example.starwarscharacters.feature_SWCharacters.data.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterEntity
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterFilmCrossRef
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterSpeciesCrossRef
import com.example.starwarscharacters.feature_SWCharacters.data.entities.FilmItemEntity
import com.example.starwarscharacters.feature_SWCharacters.data.entities.PlanetEntity
import com.example.starwarscharacters.feature_SWCharacters.data.entities.SpeciesEntity

data class CharacterWithDetails(

	@Embedded val character: CharacterEntity,

	@Relation(
		entity = SpeciesEntity::class, // КРИТИЧЕСКИ ВАЖНО: Указываем базовую сущность
		parentColumn = "id",
		entityColumn = "id",
		associateBy = Junction(
			value = CharacterSpeciesCrossRef::class,
			parentColumn = "characterId",
			entityColumn = "speciesId"
		)
	)
	val species: List<SpeciesWithPlanets>,

	@Relation(
		parentColumn = "id",
		entityColumn = "id",
		associateBy = Junction(
			value = CharacterFilmCrossRef::class,
			parentColumn = "characterId",
			entityColumn = "filmId"
		)
	)
	val films: List<FilmItemEntity>,
)
