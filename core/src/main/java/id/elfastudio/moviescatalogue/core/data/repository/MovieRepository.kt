package id.elfastudio.moviescatalogue.core.data.repository

import androidx.lifecycle.asLiveData
import androidx.paging.*
import androidx.room.withTransaction
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteMovie
import id.elfastudio.moviescatalogue.core.data.source.local.entity.MovieEntity
import id.elfastudio.moviescatalogue.core.data.source.local.room.AppDatabase
import id.elfastudio.moviescatalogue.core.data.source.remote.datasource.MovieDataSource
import id.elfastudio.moviescatalogue.core.data.source.remote.datasource.MovieRemoteMediator
import id.elfastudio.moviescatalogue.core.domain.model.Movie
import id.elfastudio.moviescatalogue.core.domain.repository.IMovieRepository
import id.elfastudio.moviescatalogue.core.utils.DataMapper
import id.elfastudio.moviescatalogue.core.utils.performGetOperation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

@ExperimentalPagingApi
class MovieRepository(
    private val dataSource: MovieDataSource,
    private val appDatabase: AppDatabase
) : IMovieRepository {

    override fun getMovie(): Flow<PagingData<Movie>> {
        val pagingSourceFactory = {
            appDatabase.movieDao().getMovies()
        }
        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = MovieRemoteMediator(
                dataSource,
                appDatabase
            )
        ).flow.map {
            it.map { movieEntity ->
                Movie(
                    movieEntity.movieId,
                    movieEntity.title,
                    movieEntity.genre,
                    movieEntity.release,
                    movieEntity.runtime,
                    movieEntity.score,
                    movieEntity.overview,
                    movieEntity.poster,
                    movieEntity.popularity,
                    false
                )
            }
        }
    }

    override fun getDetailMovie(movieId: Int) = performGetOperation(
        localData = {
            appDatabase.movieDao().getDetailMovie(movieId)
                .mapNotNull { DataMapper.favoriteMovieToMovie(it) }.asLiveData()
        },
        networkCall = {
            dataSource.getDetailMovie(movieId)
        },
        saveCallResult = {
            val data = MovieEntity(
                it.id,
                it.title,
                it.genres.map { genre -> genre.name },
                it.getRelease(),
                it.getRuntime(),
                it.getScore(),
                it.overview,
                it.posterPath,
                it.popularity
            )
            val favorite = FavoriteMovie(it.id, false)
            appDatabase.withTransaction {
                appDatabase.movieDao().insert(listOf(data))
                appDatabase.movieDao().insertFavoriteMovie(listOf(favorite))
            }
        },
        coroutineContext = Dispatchers.IO
    )

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 20, enablePlaceholders = false)
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return appDatabase.movieDao().getFavoriteMovies().mapNotNull {
            DataMapper.favoriteMoviesToMovies(it)
        }
    }

    override suspend fun favoriteMovie(movieId: Int, state: Boolean) =
        appDatabase.movieDao().favoriteMovie(FavoriteMovie(movieId, state))

}