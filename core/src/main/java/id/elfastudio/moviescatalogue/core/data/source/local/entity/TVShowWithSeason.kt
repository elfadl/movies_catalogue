package id.elfastudio.moviescatalogue.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TVShowWithSeason(
    @Embedded val tvShowEntity: TVShowEntity,
    @Relation(
        parentColumn = "tv_show_id",
        entityColumn = "tv_show_id"
    )
    val seasons: List<Season>?
)