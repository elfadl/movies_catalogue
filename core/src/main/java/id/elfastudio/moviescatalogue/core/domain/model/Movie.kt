package id.elfastudio.moviescatalogue.core.domain.model

data class Movie(
    val movieId: Int,
    val title: String,
    val genre: List<String>?,
    val release: String?,
    val runtime: String?,
    val score: Int?,
    val overview: String?,
    val poster: String?,
    val popularity: Double,
    val favorite: Boolean
){
    fun getTitleAndYear(): String = try {
        "$title (${release?.let { it.split(" ")[2] } ?: "-"})"
    }catch (e: Exception){
        e.printStackTrace()
        title
    }

    fun getGenres(): String? = genre?.joinToString(", ")
}
