package com.example.starwarscharacters.feature_SWCharacters.data.mappers

import com.example.starwarscharacters.core.util.extractId
import com.example.starwarscharacters.feature_SWCharacters.data.entities.SpeciesEntity
import com.example.starwarscharacters.feature_SWCharacters.data.relation.SpeciesWithPlanets
import com.example.starwarscharacters.feature_SWCharacters.data.remote.dto.SpeciesDto
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Species

fun SpeciesDto.toEntity(): SpeciesEntity {

	val id = url.extractId()
	val homeworldId = homeworldUrl?.extractId()

	return SpeciesEntity(
		id = id,
		name = name,
		classification = classification,
		designation = designation,
		averageHeight = averageHeight,
		eyeColors = eyeColors,
		averageLifespan = averageLifespan,
		homeworldId = homeworldId,
		language = language,
	)
}

fun SpeciesWithPlanets.toDomain(): Species {
	return Species(
		id = this.species.id,
		name = this.species.name ?: "Unknown",
		classification = this.species.classification ?: "n/a",
		designation = this.species.designation ?: "n/a",
		averageHeight = this.species.averageHeight ?: "n/a",
		eyeColors = this.species.eyeColors ?: "n/a",
		averageLifespan = this.species.averageLifespan ?: "n/a",
		language = this.species.language ?: "n/a",
		// Мапим планету через её собственный toDomain()
		planet = this.planet?.toDomain()
	)
}