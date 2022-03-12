package id.elfastudio.moviescatalogue.core.data.source.remote.datasource

import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResultAsFlow(call: suspend () -> Response<T>): Flow<ApiResponse<T>> {
        return flow {
            try {
                val response = call()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        emit(ApiResponse.Success(body))
                    }
                }
                emit(error(" ${response.code()} ${response.message()}"))
            } catch (e: Exception) {
                emit(error(e.message ?: e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ApiResponse<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return ApiResponse.Success(body)
                }
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(
        message: String,
        networkError: Boolean = true
    ): ApiResponse<T> {
        return ApiResponse.Error(
            if (networkError) "Network call has failed for a following reason: $message"
            else message
        )
    }

}