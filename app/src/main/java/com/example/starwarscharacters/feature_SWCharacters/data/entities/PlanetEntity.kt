package com.example.starwarscharacters.feature_SWCharacters.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planets")
data class PlanetEntity(

	@PrimaryKey
	val id: Long,
	val name: String?
)
