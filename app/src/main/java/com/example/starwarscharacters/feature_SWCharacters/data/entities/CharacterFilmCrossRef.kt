package com.example.starwarscharacters.feature_SWCharacters.data.entities

import androidx.room.Entity

@Entity(primaryKeys = ["characterId", "filmId"])
data class CharacterFilmCrossRef(
	val characterId: Long,
	val filmId: Long,
)
