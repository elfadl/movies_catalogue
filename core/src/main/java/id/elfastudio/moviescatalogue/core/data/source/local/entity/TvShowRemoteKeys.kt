package id.elfastudio.moviescatalogue.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.elfastudio.moviescatalogue.core.data.source.local.entity.TvShowRemoteKeys.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TvShowRemoteKeys(
    @PrimaryKey
    val repoId: Int,
    val prevKey: Int?,
    val nextKey: Int?
){

    companion object{
        const val TABLE_NAME = "tv_show_remote_keys"
    }

}
