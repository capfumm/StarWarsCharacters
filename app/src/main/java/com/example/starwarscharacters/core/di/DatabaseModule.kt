package com.example.starwarscharacters.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.starwarscharacters.feature_SWCharacters.data.dao.CharactersDao
import com.example.starwarscharacters.feature_SWCharacters.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

	@Provides
	@Singleton
	fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
		return Room.databaseBuilder(
			context = context,
			klass = AppDatabase::class.java,
			name = "characters_database"
		).build()
	}

	@Provides
	@Singleton
	fun provideDao(database: AppDatabase): CharactersDao = database.charactersDao()
}