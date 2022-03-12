package id.elfastudio.moviescatalogue.core.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat
import java.util.*

@JsonClass(generateAdapter = false)
data class DetailTvShowResponse(

	@field:Json(name="episode_run_time")
	val episodeRunTime: List<Int>,

	@field:Json(name="first_air_date")
	val firstAirDate: String,

	@field:Json(name="overview")
	val overview: String,

	@field:Json(name="original_language")
	val originalLanguage: String,

	@field:Json(name="seasons")
	val seasons: List<SeasonsItem>,

	@field:Json(name="number_of_episodes")
	val numberOfEpisodes: Int,

	@field:Json(name="languages")
	val languages: List<String>,

	@field:Json(name="type")
	val type: String,

	@field:Json(name="poster_path")
	val posterPath: String,

	@field:Json(name="origin_country")
	val originCountry: List<String>,

	@field:Json(name="genres")
	val genres: List<GenresItem>,

	@field:Json(name="original_name")
	val originalName: String,

	@field:Json(name="popularity")
	val popularity: Double,

	@field:Json(name="vote_average")
	val voteAverage: Double,

	@field:Json(name="name")
	val name: String,

	@field:Json(name="tagline")
	val tagline: String,

	@field:Json(name="id")
	val id: Int,

	@field:Json(name="number_of_seasons")
	val numberOfSeasons: Int,

	@field:Json(name="in_production")
	val inProduction: Boolean,

	@field:Json(name="vote_count")
	val voteCount: Int,

	@field:Json(name="homepage")
	val homepage: String
){
	fun getScore() = (voteAverage * 10).toInt()

	fun getRelease(): String{
		val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
		val newSdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
		return sdf.parse(firstAirDate)?.let { newSdf.format(it) } ?: "-"
	}

	fun getRuntime(): String{
		val runtime = episodeRunTime.average()
		val h = (runtime / 60).toInt()
		val m = (runtime % 60).toInt()
		return if(h > 0) "${h}h ${m}m" else "${m}m"
	}
}

data class SeasonsItem(

	@field:Json(name="air_date")
	val airDate: String?,

	@field:Json(name="overview")
	val overview: String,

	@field:Json(name="episode_count")
	val episodeCount: Int,

	@field:Json(name="name")
	val name: String,

	@field:Json(name="season_number")
	val seasonNumber: Int,

	@field:Json(name="id")
	val id: Int,

	@field:Json(name="poster_path")
	val posterPath: String
)
