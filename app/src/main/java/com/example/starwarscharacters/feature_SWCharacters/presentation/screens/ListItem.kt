package com.example.starwarscharacters.feature_SWCharacters.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.starwarscharacters.R
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character

@Composable
fun ListItem(
	character: Character,
	onItemClick: () -> Unit,
	isItemFocused: Boolean,
	onCharacterClick: (Long) -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
			.width(220.dp)
			.height(500.dp)
			.border(width = 1.dp, color = Color.Blue, shape = RoundedCornerShape(12.dp))
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
					.size(300.dp)
					.weight(0.6f)
					.clip(RoundedCornerShape(12.dp)),
				contentScale = ContentScale.Crop
			)
		} else {
			Image(painter = painterResource(R.drawable.noavatar), contentDescription = null)
		}
		Column(
			modifier = Modifier.weight(0.4f),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				character.name,
				maxLines = 1,
				textAlign = TextAlign.Center,
				style = MaterialTheme.typography.titleLarge
			)
			Text(text = "Height: ${character.height?.let { "$it cm" } ?: "n/a"}")
			Text(text = "Mass: ${character.mass?.let { "$it kg" } ?: "n/a"}")
			Text(text = "Hair: ${character.hair}")
			Text(text = "Eyes: ${character.eyes}")
		}
	}
}