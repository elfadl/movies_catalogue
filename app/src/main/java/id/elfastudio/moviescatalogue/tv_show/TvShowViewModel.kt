package id.elfastudio.moviescatalogue.tv_show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import id.elfastudio.moviescatalogue.core.domain.usecase.TvShowUseCase

class TvShowViewModel(
    tvShowUseCase: TvShowUseCase
) : ViewModel() {

    val tvShows = tvShowUseCase.getTvShow().cachedIn(viewModelScope)

    val favoriteTvShows = tvShowUseCase.getFavoriteTvShows().asLiveData()

}