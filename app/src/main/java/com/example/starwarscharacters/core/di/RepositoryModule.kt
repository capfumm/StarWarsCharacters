package com.example.starwarscharacters.core.di

import com.example.starwarscharacters.feature_SWCharacters.data.repository.CharacterRepositoryImpl
import com.example.starwarscharacters.feature_SWCharacters.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

	@Binds
	@Singleton
	abstract fun bindCharacterRepository(
		impl: CharacterRepositoryImpl
	): CharacterRepository
}