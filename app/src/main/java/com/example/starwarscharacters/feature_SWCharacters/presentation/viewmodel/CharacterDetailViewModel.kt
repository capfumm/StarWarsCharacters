package com.example.starwarscharacters.feature_SWCharacters.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarscharacters.feature_SWCharacters.domain.repository.CharacterRepository
import com.example.starwarscharacters.feature_SWCharacters.presentation.model.CharacterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
	private val repository: CharacterRepository,
	savedStateHandle: SavedStateHandle
) : ViewModel() {

	private val _isRefreshing = MutableStateFlow(false)
	private val _error = MutableStateFlow<String?>(null)

	private val characterId: Long = checkNotNull(savedStateHandle["characterId"])

	val uiState: StateFlow<CharacterUiState> = combine(
		repository.getCharacterDetails(characterId),
		_isRefreshing,
		_error
	) { details, isRefreshing, error ->
		when {
			error != null && details == null -> CharacterUiState.Error(error)
			details == null -> CharacterUiState.Loading // Ждем, пока БД отдаст объект
			else -> CharacterUiState.Success(
				character = details.character,
				filmItems = details.films,
				species = details.species,
				isRefreshing = isRefreshing // Передаем статус в Success
			)
		}
	}.stateIn(
		scope = viewModelScope,
		started = SharingStarted.WhileSubscribed(5000),
		initialValue = CharacterUiState.Loading
	)

	init {
		sync()
	}

	fun refresh() {
		sync()
	}

	private fun sync() {
		viewModelScope.launch {
			_isRefreshing.value = true
			try {
				repository.syncPlanets()
				repository.syncSpecies()
				repository.syncFilmItems()
				repository.syncCharacters()
			} catch (e: CancellationException) {
				throw e
			} catch (e: Exception) {
				_error.value = e.message ?: "Unknown error"
			} finally {
				_isRefreshing.value = false
			}
		}
	}
}