package id.elfastudio.moviescatalogue.core.data.source.remote.network

import id.elfastudio.moviescatalogue.core.data.source.remote.response.DetailMovieResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.DetailTvShowResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.MovieResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.TvShowResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getMovies(page: Int): Response<MovieResponse>

    suspend fun getTvShow(page: Int): Response<TvShowResponse>

    suspend fun getDetailMovie(movieId: Int): Response<DetailMovieResponse>

    suspend fun getDetailTvShow(tvShowId: Int): Response<DetailTvShowResponse>

}