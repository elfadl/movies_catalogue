package id.elfastudio.moviescatalogue.core.data.source.remote.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteTvShow
import id.elfastudio.moviescatalogue.core.data.source.local.entity.TVShowEntity
import id.elfastudio.moviescatalogue.core.data.source.local.entity.TVShowWithSeason
import id.elfastudio.moviescatalogue.core.data.source.local.entity.TvShowRemoteKeys
import id.elfastudio.moviescatalogue.core.data.source.local.room.AppDatabase
import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiResponse
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class TvShowRemoteMediator(
    private val tvShowDataSource: TvShowDataSource,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, TVShowWithSeason>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, TVShowWithSeason>): MediatorResult {
        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val response = tvShowDataSource.getTvShow(page)
            var isEndOfList: Boolean? = null
            when(response){
                is ApiResponse.Success -> {
                    isEndOfList = response.data.results.isNullOrEmpty()
                    appDatabase.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            appDatabase.tvShowRemoteKeysDao().clearTvShowRemoteKeys()
                            appDatabase.tvShowDao().deleteAll()
                        }
                        val prevKey = if (page == 1) null else page - 1
                        val nextKey = if (isEndOfList!!) null else page + 1
                        val remoteKeys = arrayListOf<TvShowRemoteKeys>()
                        val data = arrayListOf<TVShowEntity>()
                        val favorite = arrayListOf<FavoriteTvShow>()
                        response.data.results.forEach {
                            remoteKeys.add(TvShowRemoteKeys(repoId = it.id, prevKey = prevKey, nextKey = nextKey))
                            data.add(TVShowEntity(
                                it.id,
                                it.name,
                                null,
                                it.getRelease(),
                                null,
                                it.getScore(),
                                it.overview,
                                it.posterPath,
                                it.popularity
                            ))
                            favorite.add(FavoriteTvShow(it.id, false))
                        }
                        appDatabase.withTransaction {
                            appDatabase.tvShowRemoteKeysDao().insert(remoteKeys)
                            appDatabase.tvShowDao().insertTVShow(data)
                            appDatabase.tvShowDao().insertFavoriteTvShow(favorite)
                        }
                    }
                }
                is ApiResponse.Error -> isEndOfList = null
                is ApiResponse.Empty -> isEndOfList = null
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList!!)
        } catch (exception: IOException) {
            exception.printStackTrace()
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            exception.printStackTrace()
            return MediatorResult.Error(exception)
        } catch (exception: NullPointerException) {
            exception.printStackTrace()
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, TVShowWithSeason>): TvShowRemoteKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { tvShow -> appDatabase.tvShowRemoteKeysDao().remoteKeysTvShowId(tvShow.tvShowEntity.tvShowId) }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, TVShowWithSeason>): TvShowRemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { tvShow -> appDatabase.tvShowRemoteKeysDao().remoteKeysTvShowId(tvShow.tvShowEntity.tvShowId) }
    }

    private suspend fun getClosestRemoteKey(state: PagingState<Int, TVShowWithSeason>): TvShowRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.tvShowEntity?.tvShowId?.let { repoId ->
                appDatabase.tvShowRemoteKeysDao().remoteKeysTvShowId(repoId)
            }
        }
    }

    private suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, TVShowWithSeason>): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)

                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
                prevKey
            }
        }
    }

}