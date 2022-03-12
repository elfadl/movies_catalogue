package id.elfastudio.moviescatalogue.core.data.source.remote.response

import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*

data class MovieResponse(

	@field:Json(name="results")
	val results: List<ResultsMovie>

)

data class ResultsMovie(

	@field:Json(name="overview")
	val overview: String,

	@field:Json(name="original_language")
	val originalLanguage: String,

	@field:Json(name="original_title")
	val originalTitle: String,

	@field:Json(name="release_date")
	val releaseDate: String,

	@field:Json(name="popularity")
	val popularity: Double,

	@field:Json(name="vote_average")
	val voteAverage: Double,

	@field:Json(name="id")
	val id: Int,

	@field:Json(name="video")
	val video: Boolean,

	@field:Json(name="title")
	val title: String,

	@field:Json(name="genre_ids")
	val genreIds: List<Int>,

	@field:Json(name="vote_count")
	val voteCount: Int,

	@field:Json(name="poster_path")
	val posterPath: String?
){

	fun getScore() = (voteAverage * 10).toInt()

	fun getRelease(): String{
		val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
		val newSdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
		return try {
			sdf.parse(releaseDate)?.let { newSdf.format(it) } ?: "-"
		}catch (e: Exception){
			e.printStackTrace()
			"-"
		}
	}

}
