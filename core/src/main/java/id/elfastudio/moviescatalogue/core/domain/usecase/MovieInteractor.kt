package id.elfastudio.moviescatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import id.elfastudio.moviescatalogue.core.data.Resource
import id.elfastudio.moviescatalogue.core.domain.model.Movie
import id.elfastudio.moviescatalogue.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getMovie(): Flow<PagingData<Movie>> = movieRepository.getMovie()

    override fun getDetailMovie(movieId: Int): LiveData<Resource<Movie>> =
        movieRepository.getDetailMovie(movieId)

    override fun getFavoriteMovies(): Flow<List<Movie>> = movieRepository.getFavoriteMovies()

    override suspend fun favoriteMovie(movieId: Int, state: Boolean) =
        movieRepository.favoriteMovie(movieId, state)
}