package com.example.starwarscharacters.feature_SWCharacters.domain.model

import com.example.starwarscharacters.feature_SWCharacters.data.relation.SpeciesWithPlanets

data class CharacterDetailModel(
	val character: Character,
	val films: List<FilmItem>,
	val species: List<Species>,
)
