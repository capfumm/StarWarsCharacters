package com.example.starwarscharacters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.starwarscharacters.Navigation.routs.Screen
import com.example.starwarscharacters.core.ui.theme.StarWarsCharactersTheme
import com.example.starwarscharacters.feature_SWCharacters.presentation.screens.CharacterDetailScreen
import com.example.starwarscharacters.feature_SWCharacters.presentation.screens.ListScreen
import com.example.starwarscharacters.feature_SWCharacters.presentation.viewmodel.CharacterDetailViewModel
import com.example.starwarscharacters.feature_SWCharacters.presentation.viewmodel.ListScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			StarWarsCharactersTheme {
				StarWarsNavigation()
			}
		}
	}
}

@Composable
fun StarWarsNavigation() {
	val navController = rememberNavController()

	NavHost(
		navController = navController,
		startDestination = Screen.Characters.route
	) {
		// Экран со списком персонажей
		composable(route = Screen.Characters.route) {
			val viewModel: ListScreenViewModel = hiltViewModel()
			ListScreen(
				viewModel = viewModel,
				onCharacterClick = { characterId ->
					navController.navigate(Screen.CharacterDetail.passId(characterId))
				}
			)
		}

		// Экран деталей персонажа
		composable(
			route = Screen.CharacterDetail.route,
			arguments = listOf(navArgument("characterId") { type = NavType.LongType })
		) {
			val viewModel: CharacterDetailViewModel = hiltViewModel()
			// На этом экране мы будем получать данные из ViewModel
			CharacterDetailScreen(
				viewModel = viewModel,
				onBackClick = { navController.popBackStack() }
			)
		}
	}
}
