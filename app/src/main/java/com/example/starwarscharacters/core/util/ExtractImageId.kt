package com.example.starwarscharacters.core.util

fun String.extractId() :String {
	return this.split('/').last()
}