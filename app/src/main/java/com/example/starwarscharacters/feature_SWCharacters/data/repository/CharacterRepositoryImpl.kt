package com.example.starwarscharacters.feature_SWCharacters.data.repository

import com.example.starwarscharacters.core.util.extractId
import com.example.starwarscharacters.feature_SWCharacters.data.dao.AppDao
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterFilmCrossRef
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterSpeciesCrossRef
import com.example.starwarscharacters.feature_SWCharacters.data.mappers.toDomain
import com.example.starwarscharacters.feature_SWCharacters.data.mappers.toEntity
import com.example.starwarscharacters.feature_SWCharacters.data.remote.api.AppApi
import com.example.starwarscharacters.feature_SWCharacters.domain.model.Character
import com.example.starwarscharacters.feature_SWCharacters.domain.model.CharacterDetailModel
import com.example.starwarscharacters.feature_SWCharacters.domain.model.SpeciesDetailModel
import com.example.starwarscharacters.feature_SWCharacters.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
	private val dao: AppDao,
	private val api: AppApi
): CharacterRepository {
	override fun getAllCharacters(): Flow<List<Character>> {
		return dao.getAllCharacters().map { characterEntities ->
			characterEntities.map { it.toDomain() }
		}
	}

	override suspend fun syncSpecies() = withContext(Dispatchers.IO) {
		val species = api.getSpecies()
			.map { it.toEntity() }
		dao.upsertSpecies(species)
	}

	override suspend fun syncFilmItems() = withContext(Dispatchers.IO) {
		val filmItems = api.getFilmItem()
			.map { it.toEntity() }
		dao.upsertFilms(filmItems)
	}

	override suspend fun syncPlanets() {
		val planets = api.getPlanets()
			.map { it. toEntity() }
		dao.upsertPlanets(planets)
	}

	override fun getCharacterDetails(id: Long): Flow<CharacterDetailModel?> {
		return dao.getFullCharacterInfo(id).map { details ->
			details?.let {
				CharacterDetailModel(
					character = it.character.toDomain(),
					// Room уже вложил сюда фильмы и виды благодаря @Relation
					films = it.films.map { film -> film.toDomain() },
					species = it.species.map { spec -> spec.toDomain() }
				)
			}
		}
	}

	override fun getSpeciesWithPlanet(id: Long): Flow<SpeciesDetailModel?> {
		return dao.getSpeciesWithPlanet(id).map { relation ->
			relation?.let {
				SpeciesDetailModel(
					species = it,
					// Безопасно мапим планету, если она есть
					planet = it.planet?.toDomain()
				)
			}
		}
	}


	override suspend fun syncCharacters() = withContext(Dispatchers.IO) {
		val response = api.getAllCharacters()

		val crossRefs = response.flatMap { dto ->
			val charId = dto.url.extractId()
			dto.films?.map { filmUrl ->
				CharacterFilmCrossRef(charId, filmUrl.extractId())
			} ?: emptyList()
		}

		val speciesRefs = response.flatMap { dto ->
			val charId = dto.url.extractId()
			dto.species?.map { speciesUrl ->
				CharacterSpeciesCrossRef(charId, speciesUrl.extractId())
			} ?: emptyList()
		}

		// Сохраняем все связи в БД
		dao.upsertCharacters(response.map { it.toEntity() })
		dao.upsertCharacterFilmRefs(crossRefs)
		dao.upsertCharacterSpeciesRefs(speciesRefs)
	}

}