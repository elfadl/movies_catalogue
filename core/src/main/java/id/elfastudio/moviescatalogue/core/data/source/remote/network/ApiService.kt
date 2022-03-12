package id.elfastudio.moviescatalogue.core.data.source.remote.network

import id.elfastudio.moviescatalogue.core.data.source.remote.response.DetailMovieResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.DetailTvShowResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.MovieResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.TvShowResponse
import id.elfastudio.moviescatalogue.core.others.Url.DETAIL_MOVIE
import id.elfastudio.moviescatalogue.core.others.Url.DETAIL_TV_SHOW
import id.elfastudio.moviescatalogue.core.others.Url.GET_MOVIE
import id.elfastudio.moviescatalogue.core.others.Url.GET_TV_SHOW
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(GET_MOVIE)
    suspend fun getMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET(GET_TV_SHOW)
    suspend fun getTvShow(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<TvShowResponse>

    @GET(DETAIL_MOVIE)
    suspend fun getDetailMovie(
        @Path("movieId") movieId: Int,
        @Query("language") language: String
    ): Response<DetailMovieResponse>

    @GET(DETAIL_TV_SHOW)
    suspend fun getDetailTvShow(
        @Path("tvShowId") tvShowId: Int,
        @Query("language") language: String
    ): Response<DetailTvShowResponse>

}