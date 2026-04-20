package com.example.starwarscharacters.core.util

object PosterProvider {
	private val filmPosters = mapOf(
		"1" to "https://static.wikia.nocookie.net/starwars/images/7/75/EPI_TPM_poster.png/revision/latest?cb=20250617214241",
		"2" to "https://static.wikia.nocookie.net/starwars/images/d/dd/Attack-Clones-Poster.jpg/revision/latest?cb=20250617225730",
		"3" to "https://static.wikia.nocookie.net/starwars/images/e/e7/EPIII_RotS_poster.png/revision/latest?cb=20250617231154",
		"4" to "https://static.wikia.nocookie.net/starwars/images/4/44/1977-StarWars-theatricalposter.jpg/revision/latest?cb=20260130123909",
		"5" to "https://static.wikia.nocookie.net/starwars/images/e/e8/1980-EmpireStrikesBack-theatricalposter.jpg/revision/latest?cb=20260130125325",
		"6" to "https://static.wikia.nocookie.net/starwars/images/b/b2/ReturnOfTheJediPoster1983.jpg/revision/latest?cb=20250617080341"
	)

	fun getUrl(episodeId: String?): String? = filmPosters[episodeId]
}