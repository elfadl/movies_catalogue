package id.elfastudio.moviescatalogue.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.elfastudio.moviescatalogue.core.data.source.local.entity.TVShowEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TVShowEntity(
    @PrimaryKey
    @ColumnInfo(name = "tv_show_id")
    val tvShowId: Int,
    val title: String,
    val genre: List<String>?,
    val release: String?,
    val runtime: String?,
    val score: Int?,
    val overview: String,
    val poster: String?,
    val popularity: Double? = 0.0
){

    companion object{
        const val TABLE_NAME = "tv_shows"
    }

}
