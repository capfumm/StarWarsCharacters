package com.example.starwarscharacters.feature_SWCharacters.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.starwarscharacters.feature_SWCharacters.presentation.model.ListScreenUiState
import com.example.starwarscharacters.feature_SWCharacters.presentation.viewmodel.ListScreenViewModel
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun ListScreen(
	viewModel: ListScreenViewModel = hiltViewModel(),
	onCharacterClick: (Long) -> Unit
) {

	val uiState by viewModel.uiState.collectAsStateWithLifecycle()
	val scope = rememberCoroutineScope()
	Scaffold { paddingValues ->
		Box(
			modifier = Modifier
				.padding(paddingValues)
				.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			when (val state = uiState) {
				is ListScreenUiState.Loading ->
					Column(
						verticalArrangement = Arrangement.Center,
						horizontalAlignment = Alignment.CenterHorizontally,
						modifier = Modifier.fillMaxSize()
					) {
						Text("Loading", fontSize = 32.sp)
					}

				is ListScreenUiState.Success -> {

					val pagerState = rememberPagerState(
						pageCount = { state.items.size },
						initialPage = 0
					)

					val configuration = LocalConfiguration.current
					val horizontalPadding =
						if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
							340.dp
						} else {
							100.dp
						}

					HorizontalPager(
						state = pagerState,
						contentPadding = PaddingValues(horizontal = horizontalPadding),
						modifier = Modifier.fillMaxHeight()
					) { page ->

						val pageOffset = (page - pagerState.currentPage).toFloat()
						val pageAlpha = 1f - pageOffset.absoluteValue.coerceIn(
							minimumValue = 0f,
							maximumValue = 0.5f
						)
						ListItem(
							character = state.items[page],
							isItemFocused = pagerState.currentPage == page,
							onItemClick = {
								scope.launch {
									pagerState.animateScrollToPage(page)
								}
							},
							onCharacterClick = onCharacterClick,
							modifier = Modifier.alpha(pageAlpha)

						)
					}
				}

				is ListScreenUiState.Empty ->
					Column(
						verticalArrangement = Arrangement.Center,
						horizontalAlignment = Alignment.CenterHorizontally,
						modifier = Modifier.fillMaxSize()
					) {
						Text("No Data", fontSize = 32.sp)
					}

				is ListScreenUiState.Error -> Text(state.message ?: "Error")
			}
		}
	}
}