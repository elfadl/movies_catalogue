package id.elfastudio.moviescatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import id.elfastudio.moviescatalogue.core.data.Resource
import id.elfastudio.moviescatalogue.core.domain.model.TvShow
import id.elfastudio.moviescatalogue.core.domain.repository.ITvShowRepository
import kotlinx.coroutines.flow.Flow

class TvShowInteractor(private val tvShowRepository: ITvShowRepository) : TvShowUseCase {

    override fun getTvShow(): Flow<PagingData<TvShow>> = tvShowRepository.getTvShow()

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShow>> =
        tvShowRepository.getDetailTvShow(tvShowId)

    override fun getFavoriteTvShows(): Flow<List<TvShow>> = tvShowRepository.getFavoriteTvShows()

    override suspend fun favoriteTvShow(movieId: Int, state: Boolean) =
        tvShowRepository.favoriteTvShow(movieId, state)


}