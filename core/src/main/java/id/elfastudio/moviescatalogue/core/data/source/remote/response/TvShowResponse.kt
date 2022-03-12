package id.elfastudio.moviescatalogue.core.data.source.remote.response

import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*

data class TvShowResponse(

	@field:Json(name="results")
	val results: List<ResultsTvShow>
)

data class ResultsTvShow(

	@field:Json(name="first_air_date")
	val firstAirDate: String,

	@field:Json(name="overview")
	val overview: String,

	@field:Json(name="original_language")
	val originalLanguage: String,

	@field:Json(name="original_name")
	val originalName: String,

	@field:Json(name="popularity")
	val popularity: Double,

	@field:Json(name="vote_average")
	val voteAverage: Double,

	@field:Json(name="name")
	val name: String,

	@field:Json(name="id")
	val id: Int,

	@field:Json(name="genre_ids")
	val genreIds: List<Int>,

	@field:Json(name="vote_count")
	val voteCount: Int,

	@field:Json(name="poster_path")
	val posterPath: String,

	@field:Json(name="origin_country")
	val originCountry: List<String>
){
	fun getScore() = (voteAverage * 100 / 10).toInt()

	fun getRelease(): String{
		val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
		val newSdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
		return sdf.parse(firstAirDate)?.let { newSdf.format(it) } ?: "-"
	}
}
