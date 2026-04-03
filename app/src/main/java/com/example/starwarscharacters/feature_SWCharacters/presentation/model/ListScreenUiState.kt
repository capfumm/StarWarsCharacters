package com.example.starwarscharacters.feature_SWCharacters.presentation.model

import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character

sealed interface ListScreenUiState {
	object Loading: ListScreenUiState
	data class Success(
		val items: List<Character>,
		val isRefreshing: Boolean = false
	): ListScreenUiState
	data class Empty(val message: String?): ListScreenUiState
	data class Error(val message: String?): ListScreenUiState
}