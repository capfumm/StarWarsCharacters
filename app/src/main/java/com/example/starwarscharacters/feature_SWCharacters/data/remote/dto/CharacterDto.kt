package com.example.starwarscharacters.feature_SWCharacters.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class CharacterDto(
	@SerializedName("name") val name: String?,
	@SerializedName("height")val height: String?,
	@SerializedName("mass")val mass: String?,
	@SerializedName("hair_color")val hair: String?,
	@SerializedName("eye_color")val eyes: String?,
	@SerializedName("birth_year")val birthYear: String?,
	@SerializedName("gender")val gender: String?,
	@SerializedName("url") val url: String?
)
