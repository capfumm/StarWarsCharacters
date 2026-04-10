package com.example.starwarscharacters.feature_SWCharacters.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.starwarscharacters.core.util.PosterProvider
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character
import com.example.starwarscharacters.feature_SWCharacters.presentation.model.CharacterUiState
import com.example.starwarscharacters.feature_SWCharacters.presentation.viewmodel.CharacterDetailViewModel


@Composable
fun CharacterDetailScreen(
	viewModel: CharacterDetailViewModel = hiltViewModel(),
) {

	val uiState by viewModel.uiState.collectAsStateWithLifecycle()
	Scaffold { paddingValues ->
		Box(
			modifier = Modifier
				.padding(paddingValues)
				.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			when (val state = uiState) {
				is CharacterUiState.Loading -> {
					Text("Loading", fontSize = 32.sp)
				}

				is CharacterUiState.Error -> {
					Text(state.message ?: "Unknown error")
				}

				is CharacterUiState.Success ->
					LazyColumn(
						modifier = Modifier.fillMaxSize(),
						contentPadding = PaddingValues(16.dp),
						verticalArrangement = Arrangement.spacedBy(16.dp)
					) {
						// 1. Фото
						item {
							Column(
								horizontalAlignment = Alignment.CenterHorizontally
							){
								Text(
									text = state.character.name,
									style = MaterialTheme.typography.displayLarge,
									textAlign = TextAlign.Center
								)
								Spacer(modifier = Modifier.height(12.dp))
								AsyncImage(
									model = state.character.avatarUrl,
									contentDescription = null,
									modifier = Modifier
										.fillMaxWidth()
										.height(400.dp)
										.clip(RoundedCornerShape(12.dp)),
									contentScale = ContentScale.Crop
								)
							}
						}

						// 2. Основная инфа
						item {
							Text(
								"Basic information",
								style = MaterialTheme.typography.titleLarge,
								modifier = Modifier.padding(bottom = 12.dp)
							)
							Row(
								horizontalArrangement = Arrangement.SpaceBetween,
								modifier = Modifier
									.fillMaxWidth()
							) {
								BasicInfoColumn("Birth Year", state.character.birthYear)
								BasicInfoColumn(
									"Height",
									state.character.height?.let { "$it cm" } ?: "n/a")
								BasicInfoColumn(
									"Mass",
									state.character.mass?.let { "$it kg" } ?: "n/a")
								BasicInfoColumn("Gender", state.character.gender)
							}
						}

						// 3. Список видов (Species)
						if (state.species.isNotEmpty()) {
							item { Text("Species", style = MaterialTheme.typography.titleLarge) }
							items(state.species) { item ->
								Text("Race: ${item.name}", modifier = Modifier.padding(start = 8.dp))
								Text("Classification: ${item.classification}", modifier = Modifier.padding(start = 8.dp))
								Text("Designation: ${item.designation}", modifier = Modifier.padding(start = 8.dp))
								Text("Average Height: ${item.averageHeight}", modifier = Modifier.padding(start = 8.dp))
								Text("Homeworld: ${item.planet?.name}", modifier = Modifier.padding(start = 8.dp))
							}
						}

						// 4. Фильмы (можно сделать горизонтальный список для красоты)
						if (state.filmItems.isNotEmpty()) {
							item { Text("Films", style = MaterialTheme.typography.titleLarge) }
							item {
								LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
									items(state.filmItems) { film ->
										Column(

										){
											AsyncImage(
												model = PosterProvider.getUrl(film.episodeId),
												contentDescription = null,
												modifier = Modifier
													.height(400.dp)
											)
											val textModifier: Modifier = Modifier.padding(top = 4.dp)
											Text(text = film.title, style = MaterialTheme.typography.titleMedium, modifier = textModifier)
											Text(text = "Episode - ${film.episodeId}", style = MaterialTheme.typography.bodySmall, modifier = textModifier)
											Text(text = film.releaseDate, style = MaterialTheme.typography.bodySmall, modifier = textModifier)
										}
									}
								}
							}
						}
					}
			}
		}
	}
}

@Composable
fun BasicInfoColumn(title: String, description: String, modifier: Modifier = Modifier) {
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(title, style = MaterialTheme.typography.titleMedium, color = Color.Gray)
		Spacer(modifier.height(8.dp))
		Text(description, style = MaterialTheme.typography.titleMedium)
	}
}




