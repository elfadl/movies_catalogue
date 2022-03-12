package id.elfastudio.moviescatalogue.core.data.source.remote.datasource

import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiHelper
import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.DetailMovieResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow

class MovieDataSource(private val apiHelper: ApiHelper): BaseDataSource() {

    suspend fun getMovie(page: Int): Flow<ApiResponse<MovieResponse>> = getResultAsFlow {
        apiHelper.getMovies(page)
    }

    suspend fun getDetailMovie(movieId: Int): ApiResponse<DetailMovieResponse> = getResult {
        apiHelper.getDetailMovie(movieId)
    }

}