package com.example.starwarscharacters.feature_SWCharacters.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
	query: String,
	onQueryChange: (String) -> Unit,
	onCloseClick: () -> Unit,
	keyboardController: SoftwareKeyboardController?,
	modifier: Modifier = Modifier
) {
	Card(
		modifier = modifier
			.fillMaxWidth()
			.height(56.dp)
			.padding(horizontal = 16.dp),
		shape = RoundedCornerShape(28.dp),
		colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
		) {
			Icon(Icons.Default.Search, contentDescription = null)
			BasicTextField(
				value = query,
				onValueChange = onQueryChange,
				modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
				singleLine = true,
				textStyle = MaterialTheme.typography.bodyLarge,
				keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
				keyboardActions = KeyboardActions(onSearch = {
					// Здесь можно скрыть клавиатуру или запустить поиск
					keyboardController?.hide()
				})
			)
			IconButton(onClick = onCloseClick) {
				Icon(Icons.Default.Close, contentDescription = "Close")
			}
		}
	}
}