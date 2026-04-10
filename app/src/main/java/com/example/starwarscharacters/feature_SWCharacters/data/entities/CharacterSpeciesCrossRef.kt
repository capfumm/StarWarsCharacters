package com.example.starwarscharacters.feature_SWCharacters.data.entities

import androidx.room.Entity

@Entity(primaryKeys = ["characterId", "speciesId"])
data class CharacterSpeciesCrossRef(
	val characterId: Long,
	val speciesId: Long,
)
