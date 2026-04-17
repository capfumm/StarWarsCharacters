package com.example.starwarscharacters.feature_SWCharacters.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CharacterScreenTopBar(
	onBackClick: () -> Unit,
	onSearchClick: () -> Unit
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 8.dp, vertical = 4.dp),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically
	) {
		IconButton(
			onClick = onBackClick
		) {
			Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
		}
		IconButton(
			onClick = onSearchClick
		) {
			Icon(Icons.Filled.Search, contentDescription = null)
		}
	}
}