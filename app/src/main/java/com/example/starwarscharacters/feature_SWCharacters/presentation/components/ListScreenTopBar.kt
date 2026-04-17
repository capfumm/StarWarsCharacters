package com.example.starwarscharacters.feature_SWCharacters.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListScreenTopBar(
	onSearchClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier.background(Color.Black.copy(alpha = 0.3f), CircleShape),
		horizontalArrangement = Arrangement.End,
		verticalAlignment = Alignment.CenterVertically
	) {
		IconButton(
			onClick = onSearchClick
		) {
			Icon(Icons.Filled.Search, contentDescription = null)
		}
	}
}