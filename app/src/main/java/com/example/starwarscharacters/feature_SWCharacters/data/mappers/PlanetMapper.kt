package com.example.starwarscharacters.feature_SWCharacters.data.mappers

import com.example.starwarscharacters.core.util.extractId
import com.example.starwarscharacters.feature_SWCharacters.data.entities.PlanetEntity
import com.example.starwarscharacters.feature_SWCharacters.data.remote.dto.PlanetDto
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Planet

fun PlanetDto.toEntity(): PlanetEntity {
	return PlanetEntity(
		id = this.url.extractId(),
		name = name
	)
}

fun PlanetEntity.toDomain(): Planet {
	return Planet(
		name = name.removeUnknownOrNull()
	)
}