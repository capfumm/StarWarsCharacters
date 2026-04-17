package com.example.starwarscharacters.feature_SWCharacters.presentation.components.text_decor

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TitleText(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign = TextAlign.Start,
) =
	Text(
		text = text,
		style = MaterialTheme.typography.titleLarge,
		textAlign = textAlign,
		maxLines = 1,
		modifier = modifier
	)

@Composable
fun TitleTextSmall(
	text: String,
	modifier: Modifier = Modifier,
	textAlign: TextAlign = TextAlign.Start
) =
	Text(
		text = text,
		style = MaterialTheme.typography.titleSmall,
		textAlign = textAlign,
		modifier = modifier.padding(8.dp)
	)
