package com.example.starwarscharacters.feature_SWCharacters.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.text_decor.TitleText
import com.example.starwarscharacters.feature_SWCharacters.presentation.model.CharacterUiState

@Composable
fun BasicInfoCard(state: CharacterUiState.Success) {
	TitleText(
		text = "basic information",
		modifier = Modifier.padding(bottom = 12.dp),
	)
	Row(
		horizontalArrangement = Arrangement.SpaceBetween,
		modifier = Modifier
			.fillMaxWidth()
	) {
		BasicInfoColumn("birth year", state.character.birthYear)
		BasicInfoColumn(
			"height",
			state.character.height?.let { "$it cm" } ?: "n/a")
		BasicInfoColumn(
			"mass",
			state.character.mass?.let { "$it kg" } ?: "n/a")
		BasicInfoColumn("gender", state.character.gender)
	}
}

@Composable
fun BasicInfoColumn(title: String, description: String, modifier: Modifier = Modifier) {
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(title, style = MaterialTheme.typography.titleMedium)
		Spacer(modifier.height(8.dp))
		Text(description, style = MaterialTheme.typography.titleMedium)
	}
}