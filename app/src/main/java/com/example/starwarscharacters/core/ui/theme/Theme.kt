package com.example.starwarscharacters.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
	primary = Purple80,        // Светло-фиолетовый для акцентов
	onPrimary = Color.Black,   // Черный текст на фиолетовых кнопках

	secondary = PurpleGrey80,

	background = Color(0xFF1C1B1F), // Очень темно-серый (стандарт Material 3)
	onBackground = Color(0xFFE6E1E5), // Почти белый текст для чтения

	surface = Color(0xFF252429),    // Цвет карточек (чуть светлее фона)
	onSurface = Color(0xFFE6E1E5)
)

private val LightColorScheme = lightColorScheme(
	primary = Purple80,        // Светло-фиолетовый для акцентов
	onPrimary = Color(0xFFD8D5E8),   // Черный текст на фиолетовых кнопках

	secondary = PurpleGrey80,

	background = Color(0xFFC7C7C7), // Очень темно-серый (стандарт Material 3)
	onBackground = Color(0xFF1C191C), // Почти белый текст для чтения

	surface = Color(0xFFC7C7C7),    // Цвет карточек (чуть светлее фона)
	onSurface = Color(0xFF1D1B1D)

	/* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun StarWarsCharactersTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	// Dynamic color is available on Android 12+
	dynamicColor: Boolean = false,
	content: @Composable () -> Unit
) {
	val colorScheme = when {
		dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
			val context = LocalContext.current
			if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
		}

		darkTheme -> DarkColorScheme
		else -> LightColorScheme
	}

	MaterialTheme(
		colorScheme = colorScheme,
		typography = customTypography,
		content = content
	)
}