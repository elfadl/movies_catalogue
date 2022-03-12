package id.elfastudio.moviescatalogue.detail

import androidx.lifecycle.*
import id.elfastudio.moviescatalogue.core.domain.usecase.MovieUseCase
import id.elfastudio.moviescatalogue.core.domain.usecase.TvShowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFilmViewModel(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    val movieId = MutableLiveData(0)
    var tvShowId = MutableLiveData(0)

    val movie = movieId.switchMap { movieUseCase.getDetailMovie(it) }
    val tvShow = tvShowId.switchMap { tvShowUseCase.getDetailTvShow(it) }

    fun favoriteMovie(state: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            movieId.value?.let {
                if (it > 0)
                    movieUseCase.favoriteMovie(it, state)
            }
            tvShowId.value?.let {
                if (it > 0)
                    tvShowUseCase.favoriteTvShow(it, state)
            }
        }
    }

}