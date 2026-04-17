package com.example.starwarscharacters.feature_SWCharacters.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(
	onBackClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	IconButton(
		onClick = onBackClick,
		modifier = modifier
			.padding(12.dp)
			.clip(shape = CircleShape)
			.background(Color.Gray.copy(alpha = 0.5f))
	) {
		Icon(
			Icons.AutoMirrored.Filled.ArrowBack,
			contentDescription = null
		)
	}
}