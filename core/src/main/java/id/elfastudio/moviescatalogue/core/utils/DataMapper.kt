package id.elfastudio.moviescatalogue.core.utils

import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteMovieWithMovie
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteTvShowWithTvShow
import id.elfastudio.moviescatalogue.core.domain.model.Movie
import id.elfastudio.moviescatalogue.core.domain.model.TvShow

object DataMapper {

    fun favoriteMoviesToMovies(input: List<FavoriteMovieWithMovie>): List<Movie> =
        input.map {
            Movie(
                it.movieEntity.movieId,
                it.movieEntity.title,
                it.movieEntity.genre,
                it.movieEntity.release,
                it.movieEntity.runtime,
                it.movieEntity.score,
                it.movieEntity.overview,
                it.movieEntity.poster,
                it.movieEntity.popularity,
                it.favorite.favorite
            )
        }

    fun favoriteMovieToMovie(input: FavoriteMovieWithMovie?): Movie? = input?.let {
        Movie(
            input.movieEntity.movieId,
            input.movieEntity.title,
            input.movieEntity.genre,
            input.movieEntity.release,
            input.movieEntity.runtime,
            input.movieEntity.score,
            input.movieEntity.overview,
            input.movieEntity.poster,
            input.movieEntity.popularity,
            input.favorite.favorite
        )
    }

    fun favoriteTvShowsToTvShows(input: List<FavoriteTvShowWithTvShow>): List<TvShow> =
        input.map {
            TvShow(
                it.tvShow.tvShowEntity.tvShowId,
                it.tvShow.tvShowEntity.title,
                it.tvShow.tvShowEntity.genre,
                it.tvShow.tvShowEntity.release,
                it.tvShow.tvShowEntity.runtime,
                it.tvShow.tvShowEntity.score,
                it.tvShow.tvShowEntity.overview,
                it.tvShow.tvShowEntity.poster,
                it.tvShow.tvShowEntity.popularity,
                it.tvShow.seasons,
                it.favorite.favorite
            )
        }

    fun favoriteTvShowToTvShow(input: FavoriteTvShowWithTvShow?): TvShow? =
        input?.let {
            TvShow(
                input.tvShow.tvShowEntity.tvShowId,
                input.tvShow.tvShowEntity.title,
                input.tvShow.tvShowEntity.genre,
                input.tvShow.tvShowEntity.release,
                input.tvShow.tvShowEntity.runtime,
                input.tvShow.tvShowEntity.score,
                input.tvShow.tvShowEntity.overview,
                input.tvShow.tvShowEntity.poster,
                input.tvShow.tvShowEntity.popularity,
                input.tvShow.seasons,
                input.favorite.favorite
            )
        }

}

