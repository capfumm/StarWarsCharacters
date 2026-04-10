package com.example.starwarscharacters.feature_SWCharacters.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SpeciesDto(
	@SerializedName("name") val name: String?,
	@SerializedName("classification") val classification: String?,
	@SerializedName("designation") val designation: String?,
	@SerializedName("skin_colors") val averageHeight: String?,
	@SerializedName("eye_colors") val eyeColors: String?,
	@SerializedName("average_lifespan") val averageLifespan: String?,
	@SerializedName("homeworld") val homeworldUrl: String?,
	@SerializedName("language") val language: String?,
	@SerializedName("url") val url: String?,
)
