package id.elfastudio.moviescatalogue.core.data.repository

import androidx.lifecycle.asLiveData
import androidx.paging.*
import androidx.room.withTransaction
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteTvShow
import id.elfastudio.moviescatalogue.core.data.source.local.entity.Season
import id.elfastudio.moviescatalogue.core.data.source.local.entity.TVShowEntity
import id.elfastudio.moviescatalogue.core.data.source.local.room.AppDatabase
import id.elfastudio.moviescatalogue.core.data.source.remote.datasource.TvShowDataSource
import id.elfastudio.moviescatalogue.core.data.source.remote.datasource.TvShowRemoteMediator
import id.elfastudio.moviescatalogue.core.domain.model.TvShow
import id.elfastudio.moviescatalogue.core.domain.repository.ITvShowRepository
import id.elfastudio.moviescatalogue.core.utils.DataMapper
import id.elfastudio.moviescatalogue.core.utils.performGetOperation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

@ExperimentalPagingApi
class TvShowRepository(
    private val dataSource: TvShowDataSource,
    private val appDatabase: AppDatabase
) : ITvShowRepository {

    override fun getTvShow(): Flow<PagingData<TvShow>> {
        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = { appDatabase.tvShowDao().getTvShows() },
            remoteMediator = TvShowRemoteMediator(
                dataSource,
                appDatabase
            )
        ).flow.map {
            it.map { tvShow ->
                TvShow(
                    tvShow.tvShowEntity.tvShowId,
                    tvShow.tvShowEntity.title,
                    tvShow.tvShowEntity.genre,
                    tvShow.tvShowEntity.release,
                    tvShow.tvShowEntity.runtime,
                    tvShow.tvShowEntity.score,
                    tvShow.tvShowEntity.overview,
                    tvShow.tvShowEntity.poster,
                    tvShow.tvShowEntity.popularity,
                    tvShow.seasons,
                    false
                )
            }
        }
    }

    override fun getDetailTvShow(tvShowId: Int) = performGetOperation(
        localData = {
            appDatabase.tvShowDao().getDetailTvShow(tvShowId).mapNotNull {
                DataMapper.favoriteTvShowToTvShow(it)
            }.asLiveData()
        },
        networkCall = {
            dataSource.getDetailTvShow(tvShowId)
        },
        saveCallResult = {
            val data = TVShowEntity(
                it.id,
                it.name,
                it.genres.map { genre -> genre.name },
                it.getRelease(),
                it.getRuntime(),
                it.getScore(),
                it.overview,
                it.posterPath,
                it.popularity
            )
            val seasons = it.seasons.map { item ->
                Season(
                    item.id,
                    item.seasonNumber,
                    item.airDate?.split("-")?.get(0) ?: "-",
                    item.episodeCount,
                    data.tvShowId
                )
            }
            val favorite = FavoriteTvShow(it.id, false)
            appDatabase.withTransaction {
                appDatabase.tvShowDao().insertTVShow(listOf(data))
                appDatabase.tvShowDao().insertSeasons(seasons)
                appDatabase.tvShowDao().insertFavoriteTvShow(listOf(favorite))
            }
        },
        coroutineContext = Dispatchers.IO
    )

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 20, enablePlaceholders = false)
    }

    override fun getFavoriteTvShows() = appDatabase.tvShowDao().getFavoriteTvShows().mapNotNull{
        DataMapper.favoriteTvShowsToTvShows(it)
    }

    override suspend fun favoriteTvShow(movieId: Int, state: Boolean) =
        appDatabase.tvShowDao().favoriteTvShow(FavoriteTvShow(movieId, state))

}