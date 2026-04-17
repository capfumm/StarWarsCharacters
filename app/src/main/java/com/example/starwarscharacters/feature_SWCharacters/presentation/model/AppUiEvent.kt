package com.example.starwarscharacters.feature_SWCharacters.presentation.model

sealed interface AppUiEvent {
	data class ShowSnackbar(val message: String) : AppUiEvent
	data class NavigateToDetails(val userId: String) : AppUiEvent
	object CloseApp : AppUiEvent
}