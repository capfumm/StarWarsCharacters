package com.example.starwarscharacters.feature_SWCharacters.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Species
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.text_decor.BodyText
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.text_decor.TitleTextSmall

@Composable
fun SpeciesCard(item: Species) {
	Row {
		Column {
			TitleTextSmall(
				"Race:",
			)
			TitleTextSmall(
				"Classification:",
			)
			TitleTextSmall(
				"Designation:"
			)
			TitleTextSmall(
				"Average Height:",
			)
			TitleTextSmall(
				"Homeworld:",
			)
		}
		Column {
			BodyText(
				item.name,
			)
			BodyText(
				item.classification,
			)
			BodyText(
				item.designation,
			)
			BodyText(
				item.averageHeight,
			)
			BodyText(
				"${item.planet?.name}",
			)
		}
	}
}