package com.example.starwarscharacters.feature_SWCharacters.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.starwarscharacters.R
import com.example.starwarscharacters.core.ui.theme.StarWarsCharactersTheme
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.text_decor.BodyText
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.text_decor.TitleText

@Composable
fun ListItem(
	character: Character,
	onItemClick: () -> Unit,
	isItemFocused: Boolean,
	onCharacterClick: (Long) -> Unit,
	isLandscape: Boolean,
	modifier: Modifier = Modifier,
) {

	val borderColor = if (isItemFocused) Color.White else Color.Unspecified

	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
			.width(220.dp)
			.height(if(isLandscape) 300.dp else 450.dp)
			.clip(RoundedCornerShape(24.dp)) // Закругляем углы
			.background(MaterialTheme.colorScheme.surface.copy(alpha = 0.85f)) // Делаем чуть прозрачным
			.border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(24.dp)) // Еле заметная рамка
			.padding(16.dp)
			.clickable {
				if (isItemFocused) {
					onCharacterClick(character.id)
				} else {
					onItemClick()
				}
			}

	) {
		if (character.avatarUrl != null) {
			AsyncImage(
				model = character.avatarUrl,
				contentDescription = null,
				modifier = modifier
					.weight(0.6f)
					.clip(RoundedCornerShape(12.dp)),
				contentScale = ContentScale.Crop
			)
		} else {
			Image(painter = painterResource(R.drawable.noavatar), contentDescription = null)
		}
		Column(
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			TitleText(character.name)
			TitleText(text = "Height: ${character.height?.let { "$it cm" } ?: "n/a"}")
			TitleText(text = "Mass: ${character.mass?.let { "$it kg" } ?: "n/a"}")
			TitleText(text = "Hair: ${character.hair}")
			TitleText(text = "Eyes: ${character.eyes}")
		}
	}
}