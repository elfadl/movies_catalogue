package id.elfastudio.moviescatalogue.core.data.source.local.entity

import androidx.room.*
import id.elfastudio.moviescatalogue.core.data.source.local.entity.Season.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Season(
    @PrimaryKey
    val id: Int,
    val season: Int,
    val year: String,
    val episode: Int,
    @ColumnInfo(name = "tv_show_id")
    val tvShowId: Int
){
    companion object{
        const val TABLE_NAME = "seasons"
    }
}
