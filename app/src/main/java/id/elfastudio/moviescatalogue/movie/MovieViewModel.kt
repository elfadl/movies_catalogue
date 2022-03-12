package id.elfastudio.moviescatalogue.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import id.elfastudio.moviescatalogue.core.domain.usecase.MovieUseCase

class MovieViewModel(
    movieUseCase: MovieUseCase
) : ViewModel() {

    val movies = movieUseCase.getMovie().cachedIn(viewModelScope)

    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()

}