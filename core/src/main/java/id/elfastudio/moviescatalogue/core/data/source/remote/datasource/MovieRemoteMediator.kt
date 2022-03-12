package id.elfastudio.moviescatalogue.core.data.source.remote.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteMovie
import id.elfastudio.moviescatalogue.core.data.source.local.entity.MovieEntity
import id.elfastudio.moviescatalogue.core.data.source.local.entity.MovieRemoteKeys
import id.elfastudio.moviescatalogue.core.data.source.local.room.AppDatabase
import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class MovieRemoteMediator(
    private val movieDataSource: MovieDataSource,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieEntity>): MediatorResult {
        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val response = movieDataSource.getMovie(page)
            var isEndOfList: Boolean? = null
            when(val apiResponse = response.first()){
                is ApiResponse.Success -> {
                    isEndOfList = apiResponse.data.results.isNullOrEmpty()
                    appDatabase.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            appDatabase.movieRemoteKeysDao().clearMovieRemoteKeys()
                            appDatabase.movieDao().deleteAll()
                        }
                        val prevKey = if (page == 1) null else page - 1
                        val nextKey = if (isEndOfList!!) null else page + 1
                        val remoteKeys = arrayListOf<MovieRemoteKeys>()
                        val data = arrayListOf<MovieEntity>()
                        val favorites = arrayListOf<FavoriteMovie>()
                        apiResponse.data.results.forEach { result ->
                            remoteKeys.add(MovieRemoteKeys(repoId = result.id, prevKey = prevKey, nextKey = nextKey))
                            data.add(MovieEntity(
                                result.id,
                                result.title,
                                null,
                                result.getRelease(),
                                null,
                                result.getScore(),
                                result.overview,
                                result.posterPath,
                                result.popularity
                            ))
                            favorites.add(FavoriteMovie(result.id, false))
                        }
                        appDatabase.movieRemoteKeysDao().insert(remoteKeys)
                        appDatabase.movieDao().insert(data)
                        appDatabase.movieDao().insertFavoriteMovie(favorites)
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

    private suspend fun getFirstRemoteKey(state: PagingState<Int, MovieEntity>): MovieRemoteKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { movie -> appDatabase.movieRemoteKeysDao().remoteKeysMovieId(movie.movieId) }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, MovieEntity>): MovieRemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { movie -> appDatabase.movieRemoteKeysDao().remoteKeysMovieId(movie.movieId) }
    }

    private suspend fun getClosestRemoteKey(state: PagingState<Int, MovieEntity>): MovieRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.movieId?.let { repoId ->
                appDatabase.movieRemoteKeysDao().remoteKeysMovieId(repoId)
            }
        }
    }

    private suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, MovieEntity>): Any {
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