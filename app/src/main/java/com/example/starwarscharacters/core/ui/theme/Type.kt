package com.example.starwarscharacters.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val customTypography = Typography(
	bodyLarge = TextStyle(
		fontFamily = StarWarsFontFamily,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.5.sp
	),
    titleLarge = TextStyle(
        fontFamily = StarWarsFontFamily,
        fontWeight = FontWeight.Bold,
		fontSize = 18.sp,
		lineHeight = 24.sp,
		letterSpacing = 1.sp
    ),
	displayLarge = TextStyle(
		fontFamily = StarWarsFontFamily,
		fontWeight = FontWeight.Bold,
		fontSize = 28.sp,
		lineHeight = 24.sp,
		letterSpacing = 1.sp
	),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
	titleMedium = TextStyle(
		fontFamily = StarJediHollowFontFamily,
		fontWeight = FontWeight.Bold,
		fontSize = 16.sp,
		letterSpacing = 0.sp
	),
	bodySmall = TextStyle(
		fontFamily = StarJediHollowFontFamily,
		fontWeight = FontWeight.ExtraBold,
		fontSize = 12.sp,
		lineHeight = 28.sp,
		letterSpacing = 0.5.sp
	),
	titleSmall = TextStyle(
		fontFamily = StarWarsFontFamily,
		fontWeight = FontWeight.Bold,
		fontSize = 12.sp,
		lineHeight = 24.sp,
		letterSpacing = 1.sp
	),
)