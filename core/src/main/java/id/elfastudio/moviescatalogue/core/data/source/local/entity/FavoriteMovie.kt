package id.elfastudio.moviescatalogue.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteMovie.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FavoriteMovie(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    val favorite: Boolean
){

    companion object{
        const val TABLE_NAME = "favorite_movie"
    }

}
