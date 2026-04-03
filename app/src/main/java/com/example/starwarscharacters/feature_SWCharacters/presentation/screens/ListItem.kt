package com.example.starwarscharacters.feature_SWCharacters.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character

@Composable
fun ListItem(
	character: Character
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier.fillMaxSize()
	) {
		AsyncImage(
			model = character.avatarUrl,
			contentDescription = null,
			modifier = Modifier
				.size(300.dp)
				.clip(CircleShape),
			contentScale = ContentScale.Crop
		)
		Text(character.name, fontSize = 36.sp)
		Row() {
			Text(text = "Height: $character.height cm, ")
			Text(text = "Mass: $character.mass kg, ")
			Text(text = "Hair: $character.hair, ")
			Text(text = "Eyes: $character.eyes, ")
		}
	}
}