package id.elfastudio.moviescatalogue.movie

import androidx.lifecycle.ViewModel
import id.elfastudio.moviescatalogue.core.domain.usecase.MovieUseCase

class FakeMovieViewModel(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    fun movies() = movieUseCase.getMovie()

    fun favoriteMovies() = movieUseCase.getFavoriteMovies()
}
