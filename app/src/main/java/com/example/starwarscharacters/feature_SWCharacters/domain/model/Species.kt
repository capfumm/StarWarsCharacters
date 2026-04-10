package com.example.starwarscharacters.feature_SWCharacters.domain.model

data class Species(
	val id: Long,
	val name: String,
	val classification: String,
	val designation: String,
	val averageHeight: String,
	val eyeColors: String,
	val averageLifespan: String,
	val language: String,
	val planet: Planet?
)
