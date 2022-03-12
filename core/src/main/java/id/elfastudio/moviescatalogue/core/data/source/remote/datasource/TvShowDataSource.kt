package id.elfastudio.moviescatalogue.core.data.source.remote.datasource

import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiHelper
import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.DetailTvShowResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.TvShowResponse

class TvShowDataSource(private val apiHelper: ApiHelper) : BaseDataSource() {

    suspend fun getTvShow(page: Int): ApiResponse<TvShowResponse> = getResult {
        apiHelper.getTvShow(page)
    }

    suspend fun getDetailTvShow(tvShowId: Int): ApiResponse<DetailTvShowResponse> = getResult {
        apiHelper.getDetailTvShow(tvShowId)
    }

}