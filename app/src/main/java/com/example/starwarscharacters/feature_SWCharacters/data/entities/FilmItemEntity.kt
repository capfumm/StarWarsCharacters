package com.example.starwarscharacters.feature_SWCharacters.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmItems")
data class FilmItemEntity(

	@PrimaryKey
	val id: Long,
	val title: String?,
	val episodeId: String?,
	val releaseDate: String?,
	val filmImageUrl: String?
)
