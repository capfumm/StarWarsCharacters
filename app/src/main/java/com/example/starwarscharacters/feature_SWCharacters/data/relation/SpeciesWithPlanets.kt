package com.example.starwarscharacters.feature_SWCharacters.data.relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterSpeciesCrossRef
import com.example.starwarscharacters.feature_SWCharacters.data.entities.PlanetEntity
import com.example.starwarscharacters.feature_SWCharacters.data.entities.SpeciesEntity


data class SpeciesWithPlanets(
	@Embedded val species: SpeciesEntity,
	@Relation(
		parentColumn = "homeworldId",
		entityColumn = "id"
	)
	val planet: PlanetEntity?
)
