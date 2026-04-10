package com.example.starwarscharacters.core.util

fun String?.extractId(): Long{
	return this
		?.trimEnd('/')
		?.substringAfterLast('/')
		?.toLongOrNull() ?: 0L
}