package com.example.starwarscharacters.feature_SWCharacters.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "species")
data class SpeciesEntity(

	@PrimaryKey
	val id: Long,
	val name: String?,
	val classification: String?,
	val designation: String?,
	val averageHeight: String?,
	val eyeColors: String?,
	val averageLifespan: String?,
	val homeworldId: Long?,
	val language: String?,
)
