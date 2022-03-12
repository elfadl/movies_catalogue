package id.elfastudio.moviescatalogue.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.elfastudio.moviescatalogue.core.data.source.local.entity.MovieEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    val title: String,
    val genre: List<String>?,
    val release: String?,
    val runtime: String?,
    val score: Int?,
    val overview: String?,
    val poster: String?,
    val popularity: Double
){

    companion object{
        const val TABLE_NAME = "movies"
    }

}
