package com.example.starwarscharacters.core.util

import java.text.SimpleDateFormat
import java.util.Locale


fun formatToDate(dateString: String?): String {
	if (dateString.isNullOrBlank()) return "н/д"

	return try {
		// 1. Читаем из API (1977-05-27)
		val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
		val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale.US)
		val date = inputFormat.parse(dateString)
		outputFormat.format(date!!)
	} catch (e: Exception) {
		dateString
	}
}