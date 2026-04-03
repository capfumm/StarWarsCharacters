package com.example.starwarscharacters.feature_SWCharacters.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.starwarscharacters.feature_SWCharacters.presentation.model.ListScreenUiState
import com.example.starwarscharacters.feature_SWCharacters.presentation.viewmodel.ListViewModel

@Composable
fun ListScreen(
	viewModel: ListViewModel = hiltViewModel()
) {

	val uiState by viewModel.uiState.collectAsStateWithLifecycle()
	Scaffold() { paddingValues ->
		when (val state = uiState) {
			is ListScreenUiState.Loading -> Text("sd")
			is ListScreenUiState.Success -> {
				LazyRow(
					modifier = Modifier.padding(paddingValues)
				) {
					items(state.items) { character ->
						ListItem(character)
					}
				}
			}

			is ListScreenUiState.Error -> Text(state.message ?: "Error")
		}
	}
}