package com.example.starwarscharacters.feature_SWCharacters.domain.model

import com.example.starwarscharacters.feature_SWCharacters.data.relation.SpeciesWithPlanets

data class SpeciesDetailModel(
	val species: SpeciesWithPlanets,
	val planet: Planet?
)
