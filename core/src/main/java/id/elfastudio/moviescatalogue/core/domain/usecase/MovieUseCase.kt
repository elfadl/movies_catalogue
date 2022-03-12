package id.elfastudio.moviescatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import id.elfastudio.moviescatalogue.core.data.Resource
import id.elfastudio.moviescatalogue.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovie(): Flow<PagingData<Movie>>
    fun getDetailMovie(movieId: Int): LiveData<Resource<Movie>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    suspend fun favoriteMovie(movieId: Int, state: Boolean)
}