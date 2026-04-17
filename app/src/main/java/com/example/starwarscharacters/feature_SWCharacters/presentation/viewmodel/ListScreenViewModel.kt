package com.example.starwarscharacters.feature_SWCharacters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarscharacters.feature_SWCharacters.domain.repository.CharacterRepository
import com.example.starwarscharacters.feature_SWCharacters.presentation.model.ListScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
	private val repository: CharacterRepository,
) : ViewModel() {

	private val _isRefreshing = MutableStateFlow(false)
	private val _error = MutableStateFlow<String?>(null)

	private val _searchQuery = MutableStateFlow("")
	val searchQuery = _searchQuery.asStateFlow()

	val uiState: StateFlow<ListScreenUiState> =
		combine(
			repository.getAllCharacters(),
			_isRefreshing,
			_error,
			_searchQuery
		) { characters, refreshing, error, query ->

			val filteredItems = if (query.isBlank()){
				characters
			} else {
				characters.filter { it.name.contains(query, ignoreCase = true) }
			}

			ListScreenUiState(
				items = filteredItems,
				isRefreshing = refreshing,
				error = error,
				isLoading = refreshing && characters.isEmpty()
			)
		}
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(5000),
				initialValue = ListScreenUiState(isLoading = true)
			)

	init {
		sync()
	}

	fun refresh() {
		sync()
	}

	fun clearError() {
		_error.value = null
	}

	private fun sync() {
		viewModelScope.launch {
			_error.value = null
			_isRefreshing.value = true
			try {
				repository.syncCharacters() // Room сам толкнет новые данные в Flow
			} catch (e: CancellationException) {
				throw e
			} catch (e: Exception) {
				_error.value = e.message ?: "Unknown error"
			} finally {
				_isRefreshing.value = false
			}
		}
	}

	fun onSearchQueryChange(query: String) {
		_searchQuery.value = query
	}
}