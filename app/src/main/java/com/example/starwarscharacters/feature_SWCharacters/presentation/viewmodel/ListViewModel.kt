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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class ListViewModel(private val repository: CharacterRepository) : ViewModel() {

	private val _isRefreshing = MutableStateFlow(false)
	private val _error = MutableStateFlow<String?>(null)

	val uiState: StateFlow<ListScreenUiState> =
		combine(
			repository.getAllCharacters(),
			_isRefreshing,
			_error
		) { characters, isRefreshing, error ->
			val state: ListScreenUiState = when {
				error != null -> ListScreenUiState.Error(error)
				characters.isEmpty() && !isRefreshing -> ListScreenUiState.Empty(message = "No data")
				else -> ListScreenUiState.Success(items = characters, isRefreshing = false)
			}
			state
		}
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(5000),
				initialValue = ListScreenUiState.Loading
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
}