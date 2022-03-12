package id.elfastudio.moviescatalogue.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteTvShow.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FavoriteTvShow(
    @PrimaryKey
    @ColumnInfo(name = "tv_show_id")
    val tvShowId: Int,
    val favorite: Boolean
){

    companion object{
        const val TABLE_NAME = "favorite_tv_show"
    }

}
