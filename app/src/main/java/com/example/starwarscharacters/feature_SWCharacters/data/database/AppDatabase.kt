package com.example.starwarscharacters.feature_SWCharacters.data.database

import androidx.room.RoomDatabase
import com.example.starwarscharacters.feature_SWCharacters.data.dao.CharactersDao

abstract class AppDatabase: RoomDatabase() {
	abstract fun charactersDao(): CharactersDao
}