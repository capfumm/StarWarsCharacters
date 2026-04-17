package com.example.starwarscharacters.feature_SWCharacters.presentation.model

import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character

data class ListScreenUiState (
	val items: List<Character> = emptyList(),
	val isLoading: Boolean = false,
	val isRefreshing: Boolean = false,
	val error: String? = null
)