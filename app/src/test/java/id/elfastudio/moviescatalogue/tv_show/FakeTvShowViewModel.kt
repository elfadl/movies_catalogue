package id.elfastudio.moviescatalogue.tv_show

import androidx.lifecycle.ViewModel
import id.elfastudio.moviescatalogue.core.domain.usecase.TvShowUseCase

class FakeTvShowViewModel(
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    fun tvShows() = tvShowUseCase.getTvShow()

    fun favoriteTvShows() = tvShowUseCase.getFavoriteTvShows()

}