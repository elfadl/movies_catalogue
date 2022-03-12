package id.elfastudio.moviescatalogue.core.domain.model

import id.elfastudio.moviescatalogue.core.data.source.local.entity.Season

data class TvShow(
    val tvShowId: Int,
    val title: String,
    val genre: List<String>?,
    val release: String?,
    val runtime: String?,
    val score: Int?,
    val overview: String,
    val poster: String?,
    val popularity: Double? = 0.0,
    val seasons: List<Season>?,
    val favorite: Boolean
){

    fun getTitleAndYear(): String = "$title (${release?.let { it.split(" ")[2] } ?: "-"})"

    fun getGenres() = genre?.joinToString(", ")

}
