package com.example.starwarscharacters.feature_SWCharacters.data.mappers

import com.example.starwarscharacters.core.util.extractId
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterEntity
import com.example.starwarscharacters.feature_SWCharacters.data.remote.dto.CharacterDto
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character

fun String?.removeUnknownOrNull(): String {
	return if (this == "unknown" || this == null || this == "none") "n/a" else this
}

fun CharacterEntity.toDomain(): Character {
	return Character(
		id = id,
		avatarUrl = avatarUrl.removeUnknownOrNull(),
		name = name.removeUnknownOrNull(),
		height = height?.toIntOrNull(),
		mass = mass?.toIntOrNull(),
		hair = hair.removeUnknownOrNull(),
		eyes = eyes.removeUnknownOrNull(),
		birthYear = birthYear.removeUnknownOrNull(),
		gender = gender.removeUnknownOrNull()
	)
}

fun CharacterDto.toEntity(): CharacterEntity {
	val id = this.url.extractId()

	val avatarUrl = "https://vieraboschkova.github.io/swapi-gallery/static/assets/img/people/$id.jpg"

	return CharacterEntity(
		id = id,
		avatarUrl = avatarUrl,
		name = name,
		height = height,
		mass = mass,
		hair = hair,
		eyes = eyes,
		birthYear = birthYear,
		gender = gender,
	)
}
