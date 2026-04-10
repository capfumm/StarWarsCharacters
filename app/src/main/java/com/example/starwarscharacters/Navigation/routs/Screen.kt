package com.example.starwarscharacters.Navigation.routs

sealed class Screen(val route: String) {
	object CharacterDetail: Screen("character_detail_screen/{characterId}") {
		fun passId(id: Long) = "character_detail_screen/$id"
	}
	object Characters: Screen("characters_screen")
}