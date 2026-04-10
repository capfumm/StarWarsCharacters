package com.example.starwarscharacters.feature_SWCharacters.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwarscharacters.feature_SWCharacters.data.dao.AppDao
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterEntity
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterFilmCrossRef
import com.example.starwarscharacters.feature_SWCharacters.data.entities.CharacterSpeciesCrossRef
import com.example.starwarscharacters.feature_SWCharacters.data.entities.FilmItemEntity
import com.example.starwarscharacters.feature_SWCharacters.data.entities.PlanetEntity
import com.example.starwarscharacters.feature_SWCharacters.data.entities.SpeciesEntity

@Database(
	entities = [
		CharacterEntity::class,
		FilmItemEntity::class,
		SpeciesEntity::class,
		PlanetEntity::class,
		CharacterFilmCrossRef::class,
		CharacterSpeciesCrossRef::class,
	],
	version = 4,
	exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun provideAppDao(): AppDao
}