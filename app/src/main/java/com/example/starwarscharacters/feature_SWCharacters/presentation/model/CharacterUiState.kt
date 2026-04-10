package com.example.starwarscharacters.feature_SWCharacters.presentation.model

import com.example.starwarscharacters.feature_SWCharacters.data.relation.SpeciesWithPlanets
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character
import com.example.starwarscharacters.feature_SWCharacters.domain.model.FilmItem
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Species

interface CharacterUiState {
	object Loading: CharacterUiState
	data class Success(
		val species: List<Species>,
		val filmItems: List<FilmItem>,
		val character: Character,
		val isRefreshing: Boolean = false
	): CharacterUiState
	data class Error(val message: String?): CharacterUiState
}