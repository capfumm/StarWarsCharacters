package com.example.starwarscharacters.feature_SWCharacters.domain.model

data class Character(
	val id: Long,
	val avatarUrl: String,
	val name: String,
	val height: Int?,
	val mass: Int?,
	val hair: String,
	val eyes: String,
	val birthYear: String,
	val gender: String,
)