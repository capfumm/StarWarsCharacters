package com.example.starwarscharacters.feature_SWCharacters.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FilmItemDto(
	@SerializedName ("title") val title: String?,
	@SerializedName ("episode_id") val episodeId: String?,
	@SerializedName ("release_date") val releaseDate: String?,
	@SerializedName("url") val url: String?,
)
