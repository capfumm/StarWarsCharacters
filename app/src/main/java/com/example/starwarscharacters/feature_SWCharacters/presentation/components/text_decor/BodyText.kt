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
fun BodyText(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Start, color: Color = Color.Unspecified) =
	Text(
		text = text,
		style = MaterialTheme.typography.bodySmall,
		textAlign = textAlign,
		modifier = modifier.padding(8.dp)
	)

@Composable
fun LargeBodyText(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Start, color: Color = Color.Unspecified) =
	Text(
		text = text,
		style = MaterialTheme.typography.bodyLarge,
		textAlign = textAlign,
		modifier = modifier.padding(8.dp)
	)