package id.elfastudio.moviescatalogue.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FavoriteTvShowWithTvShow(
    @Embedded val tvShow: TVShowWithSeason,
    @Relation(
        parentColumn = "tv_show_id",
        entityColumn = "tv_show_id"
    )
    val favorite: FavoriteTvShow
)
