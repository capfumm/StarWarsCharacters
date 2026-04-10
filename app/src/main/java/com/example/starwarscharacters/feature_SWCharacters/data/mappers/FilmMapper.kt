package com.example.starwarscharacters.feature_SWCharacters.data.mappers

import com.example.starwarscharacters.core.util.PosterProvider
import com.example.starwarscharacters.core.util.extractId
import com.example.starwarscharacters.core.util.formatToDate
import com.example.starwarscharacters.feature_SWCharacters.data.entities.FilmItemEntity
import com.example.starwarscharacters.feature_SWCharacters.data.remote.dto.FilmItemDto
import com.example.starwarscharacters.feature_SWCharacters.domain.model.FilmItem

fun FilmItemDto.toEntity(): FilmItemEntity {

	val id = this.url.extractId()

	return FilmItemEntity(
		id = id,
		title = title,
		episodeId = episodeId,
		releaseDate = releaseDate,
		filmImageUrl = PosterProvider.getUrl(episodeId)
	)
}

fun FilmItemEntity.toDomain(): FilmItem {
	
	return FilmItem(
		filmImageUrl = filmImageUrl.removeUnknownOrNull(),
		title = title.removeUnknownOrNull(),
		episodeId = episodeId.removeUnknownOrNull(),
		releaseDate = formatToDate(releaseDate)
	)
}