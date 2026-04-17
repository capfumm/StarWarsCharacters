package com.example.starwarscharacters.feature_SWCharacters.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.starwarscharacters.core.util.PosterProvider
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.BackButton
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.BasicInfoCard
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.FilmCard
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.PhotoColumn
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.SpeciesCard
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.text_decor.TitleText
import com.example.starwarscharacters.feature_SWCharacters.presentation.model.CharacterUiState
import com.example.starwarscharacters.feature_SWCharacters.presentation.viewmodel.CharacterDetailViewModel


@Composable
fun CharacterDetailScreen(
	onBackClick: () -> Unit,
	viewModel: CharacterDetailViewModel = hiltViewModel(),
) {

	val uiState by viewModel.uiState.collectAsStateWithLifecycle()
	Scaffold() { paddingValues ->
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues),
			contentAlignment = Alignment.Center
		) {
			PullToRefreshBox(
				isRefreshing = (uiState as? CharacterUiState.Success)?.isRefreshing ?: false,
				onRefresh = { viewModel.refresh() }
			) {
				when (val state = uiState) {
					is CharacterUiState.Loading -> {
						Text("Loading", fontSize = 32.sp)
					}

					is CharacterUiState.Error -> {
						Text(state.message ?: "Unknown error")
					}

					is CharacterUiState.Success ->
						Box(modifier = Modifier.fillMaxSize()) {

							LazyColumn(
								contentPadding = PaddingValues(16.dp),
								verticalArrangement = Arrangement.spacedBy(16.dp)
							) {
								// 1. Name and photo
								item {
									PhotoColumn(state)
								}

								// 2. Basic info
								item {
									BasicInfoCard(state)
								}

								// 3. Species
								if (state.species.isNotEmpty()) {
									item { TitleText("species") }
									items(state.species) { item ->
										SpeciesCard(item)
									}
								}

								// 4. Films
								if (state.filmItems.isNotEmpty()) {
									item { TitleText("films") }
									item {
										LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
											items(state.filmItems) { film ->
												FilmCard(film)
											}
										}
									}
								}
							}
							BackButton(
								onBackClick = onBackClick,
								modifier = Modifier
									.align(Alignment.TopStart)
							)
						}
				}
			}
		}
	}
}






