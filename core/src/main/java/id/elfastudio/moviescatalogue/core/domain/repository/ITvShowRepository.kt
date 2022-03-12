package id.elfastudio.moviescatalogue.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import id.elfastudio.moviescatalogue.core.data.Resource
import id.elfastudio.moviescatalogue.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {

    fun getTvShow(): Flow<PagingData<TvShow>>

    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<TvShow>>

    fun getFavoriteTvShows(): Flow<List<TvShow>>

    suspend fun favoriteTvShow(movieId: Int, state: Boolean)

}