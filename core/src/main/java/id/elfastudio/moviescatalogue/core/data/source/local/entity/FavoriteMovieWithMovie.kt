package id.elfastudio.moviescatalogue.core.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FavoriteMovieWithMovie(
    @Embedded val movieEntity: MovieEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "movie_id"
    )
    val favorite: FavoriteMovie
)
