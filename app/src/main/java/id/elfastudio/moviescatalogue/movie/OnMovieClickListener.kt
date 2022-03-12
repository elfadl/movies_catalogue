package id.elfastudio.moviescatalogue.movie

import id.elfastudio.moviescatalogue.core.domain.model.Movie

interface OnMovieClickListener {

    fun onMovieClicked(movie: Movie)

}