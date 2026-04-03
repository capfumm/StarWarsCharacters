package com.example.starwarscharacters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.starwarscharacters.core.ui.theme.StarWarsCharactersTheme
import com.example.starwarscharacters.feature_SWCharacters.presentation.screens.ListItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			StarWarsCharactersTheme {
				ListItem(
					url = "https://vieraboschkova.github.io/swapi-gallery/static/assets/img/people/1.jpg",
					name = "Luke",
					height = "123",
					mass = "23",
					hair = "black",
					eyes = "blue"
				)
			}
		}
	}
}