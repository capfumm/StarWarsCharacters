package com.example.starwarscharacters.feature_SWCharacters.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.starwarscharacters.core.util.PosterProvider
import com.example.starwarscharacters.feature_SWCharacters.domain.model.FilmItem

@Composable
fun FilmCard(film: FilmItem) {
	Column{
		AsyncImage(
			model = PosterProvider.getUrl(film.episodeId),
			contentDescription = null,
			modifier = Modifier
				.height(400.dp)
		)
		val textModifier: Modifier = Modifier.padding(top = 4.dp)
		Text(text = film.title, style = MaterialTheme.typography.titleLarge, modifier = textModifier)
		Text(text = "Episode - ${film.episodeId}", style = MaterialTheme.typography.bodySmall, modifier = textModifier)
		Text(text = film.releaseDate, style = MaterialTheme.typography.bodySmall, modifier = textModifier)
	}
}