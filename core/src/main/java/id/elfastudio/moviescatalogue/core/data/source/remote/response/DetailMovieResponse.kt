package id.elfastudio.moviescatalogue.core.data.source.remote.response

import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*

data class DetailMovieResponse(

	@field:Json(name="overview")
	val overview: String,

	@field:Json(name="original_language")
	val originalLanguage: String,

	@field:Json(name="original_title")
	val originalTitle: String,

	@field:Json(name="runtime")
	val runtime: Int,

	@field:Json(name="video")
	val video: Boolean,

	@field:Json(name="title")
	val title: String,

	@field:Json(name="poster_path")
	val posterPath: String?,

	@field:Json(name="release_date")
	val releaseDate: String,

	@field:Json(name="genres")
	val genres: List<GenresItem>,

	@field:Json(name="popularity")
	val popularity: Double,

	@field:Json(name="vote_average")
	val voteAverage: Double,

	@field:Json(name="tagline")
	val tagline: String,

	@field:Json(name="id")
	val id: Int,

	@field:Json(name="vote_count")
	val voteCount: Int,

	@field:Json(name="status")
	val status: String
){
	fun getScore() = (voteAverage * 10).toInt()

	fun getRelease(): String{
		val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
		val newSdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
		return sdf.parse(releaseDate)?.let { newSdf.format(it) } ?: "-"
	}

	fun getRuntime(): String{
		val h = runtime / 60
		val m = runtime % 60
		return if(h > 0) "${h}h ${m}m" else "${m}m"
	}
}

data class GenresItem(

	@field:Json(name="name")
	val name: String,

	@field:Json(name="id")
	val id: Int
)
