package id.elfastudio.moviescatalogue.core.data.source.remote.network

import id.elfastudio.moviescatalogue.core.data.source.remote.response.DetailMovieResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.DetailTvShowResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.MovieResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.TvShowResponse
import retrofit2.Response

class ApiHelperImpl constructor(private val apiService: ApiService) : ApiHelper {

    private val language = "en-US"

    override suspend fun getMovies(page: Int): Response<MovieResponse> = apiService.getMovies(language, page)

    override suspend fun getTvShow(page: Int): Response<TvShowResponse> = apiService.getTvShow(language, page)

    override suspend fun getDetailMovie(movieId: Int): Response<DetailMovieResponse> =
        apiService.getDetailMovie(movieId, language)

    override suspend fun getDetailTvShow(tvShowId: Int): Response<DetailTvShowResponse> =
        apiService.getDetailTvShow(tvShowId, language)
}