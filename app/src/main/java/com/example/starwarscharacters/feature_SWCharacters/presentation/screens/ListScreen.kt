package com.example.starwarscharacters.feature_SWCharacters.presentation.screens

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.starwarscharacters.R
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.ListItem
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.SearchBar
import com.example.starwarscharacters.feature_SWCharacters.presentation.components.text_decor.TitleText
import com.example.starwarscharacters.feature_SWCharacters.presentation.model.ListScreenUiState
import com.example.starwarscharacters.feature_SWCharacters.presentation.viewmodel.ListScreenViewModel
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun ListScreen(
	viewModel: ListScreenViewModel = hiltViewModel(),
	onCharacterClick: (Long) -> Unit,

) {

	val state by viewModel.uiState.collectAsStateWithLifecycle()
	val snackbarHostState = remember { SnackbarHostState() }
	val scope = rememberCoroutineScope()

	val itemsCount = state.items.size
	// 2. rememberPagerState ДОЛЖЕН быть на верхнем уровне, чтобы не пересоздаваться
	val pagerState = rememberPagerState(
		pageCount = { itemsCount }
	)

	var isSearchActive by remember { mutableStateOf(false) }

	val focusRequester = remember { FocusRequester() }
	val softwareKeyboardController = LocalSoftwareKeyboardController.current

	val configuration = LocalConfiguration.current
	val horizontalPadding =
		if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			340.dp
		} else {
			100.dp
		}

	val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

	LaunchedEffect(state.error) {
		state.error?.let { message ->
			snackbarHostState.showSnackbar(
				message = message,
				actionLabel = "Ok"
			)
			viewModel.clearError() // Нужно добавить метод сброса ошибки в VM
		}
	}

	Scaffold(
		snackbarHost = { SnackbarHost(snackbarHostState) }
	) { paddingValues ->

		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues),
			contentAlignment = Alignment.Center
		) {
			PullToRefreshBox(
				isRefreshing = state.isRefreshing,
				onRefresh = { viewModel.refresh() },
				modifier = Modifier.fillMaxSize()
			) {
					Box(
						modifier = Modifier
							.fillMaxSize()
							.paint(
								painter = painterResource(R.drawable.background),
								contentScale = ContentScale.Crop
							)
					) {
						if (state.isLoading) {
							CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
						}
						Text(
							text = "SWCharacters",
							modifier = Modifier
								.align(Alignment.TopCenter)
								.padding(top = 16.dp),
							style = MaterialTheme.typography.displayLarge,
							color = Color.White
						)
						Column(
							modifier = Modifier.fillMaxSize()
						) {
							if (state.items.isEmpty() && !state.isLoading) {
								Box(
									modifier = Modifier.fillMaxSize(),
									contentAlignment = Alignment.Center
								) {
									TitleText("No characters found")
								}
							} else {
								HorizontalPager(
									state = pagerState,
									contentPadding = PaddingValues(horizontal = horizontalPadding),
									modifier = Modifier
										.weight(0.8f)
										.verticalScroll(rememberScrollState())
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
										isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE,
										modifier = Modifier.alpha(pageAlpha)

									)
								}
								Row(
									modifier = Modifier
										.height(50.dp)
										.fillMaxWidth(),
									horizontalArrangement = Arrangement.Center
								) {
									Text(
										text = "${pagerState.currentPage + 1} / ${state.items.size}",
										style = MaterialTheme.typography.bodyLarge,
										color = Color.Gray
									)
								}
							}
						}

						Box(
							modifier = Modifier
								.fillMaxWidth()
								.padding(top = 16.dp),
							contentAlignment = Alignment.CenterEnd
						) {
							AnimatedVisibility(
								visible = isSearchActive,
								enter = fadeIn() + expandHorizontally(expandFrom = Alignment.End),
								exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.End)
							) {
								SearchBar(
									query = searchQuery,
									onQueryChange = { viewModel.onSearchQueryChange(it) },
									onCloseClick = {
										isSearchActive = false
										viewModel.onSearchQueryChange("")
										softwareKeyboardController?.hide()
									},
									keyboardController = softwareKeyboardController,
									modifier = Modifier.focusRequester(focusRequester),

									)
							}

							LaunchedEffect(isSearchActive) {
								if (isSearchActive) {
									focusRequester.requestFocus()
									softwareKeyboardController?.show()
								}
							}

							AnimatedVisibility(
								visible = !isSearchActive,
								modifier = Modifier
									.align(Alignment.TopEnd)
									.padding(end = 16.dp)
							) {
								IconButton(
									onClick = { isSearchActive = true },
									modifier = Modifier.background(
										Color.Black.copy(0.3f),
										CircleShape
									)
								) {
									Icon(Icons.Default.Search, null, tint = Color.White)
								}
							}
						}
					}

			}
		}

	}
}
