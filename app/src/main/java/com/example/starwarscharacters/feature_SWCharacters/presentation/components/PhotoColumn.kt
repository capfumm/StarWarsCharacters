package com.example.starwarscharacters.feature_SWCharacters.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.starwarscharacters.core.ui.theme.StarWarsFontFamily
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.text_decor.TitleText
import com.example.starwarscharacters.feature_SWCharacters.presentation.model.CharacterUiState

@Composable
fun PhotoColumn(state: CharacterUiState.Success){
	Column(
		horizontalAlignment = Alignment.CenterHorizontally
	) {

		Text(
			text = state.character.name,
			textAlign = TextAlign.Center,
			fontFamily = StarWarsFontFamily,
			fontSize = 32.sp,
		)

		Spacer(modifier = Modifier.height(12.dp))
		AsyncImage(
			model = state.character.avatarUrl,
			contentDescription = null,
			modifier = Modifier
				.fillMaxWidth()
				.height(400.dp)
				.clip(RoundedCornerShape(12.dp)),
			contentScale = ContentScale.Crop
		)
	}
}