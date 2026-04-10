package com.example.starwarscharacters.feature_SWCharacters.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlanetDto(
	@SerializedName("name") val name: String?,
	@SerializedName("url") val url: String?
)
