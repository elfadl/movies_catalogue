package id.elfastudio.moviescatalogue.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.elfastudio.moviescatalogue.core.domain.usecase.MovieUseCase
import id.elfastudio.moviescatalogue.core.domain.usecase.TvShowUseCase

class FakeDetailFilmViewModel(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    val movieId = MutableLiveData(0)
    var tvShowId = MutableLiveData(0)

    fun movie() = movieId.switchMap { movieUseCase.getDetailMovie(it) }
    fun tvShow() = tvShowId.switchMap { tvShowUseCase.getDetailTvShow(it) }

}