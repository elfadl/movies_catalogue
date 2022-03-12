package id.elfastudio.moviescatalogue.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.elfastudio.moviescatalogue.core.data.source.local.entity.MovieRemoteKeys.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MovieRemoteKeys(
    @PrimaryKey
    val repoId: Int,
    val prevKey: Int?,
    val nextKey: Int?
){

    companion object{
        const val TABLE_NAME = "movie_remote_keys"
    }

}
