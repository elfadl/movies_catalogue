@file:Suppress("KotlinDeprecation")

package id.elfastudio.moviescatalogue.core.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import id.elfastudio.moviescatalogue.core.data.Resource
import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@SuppressWarnings("uselsess cast")
fun <T, A> performGetOperation(
    localData: () -> LiveData<T>,
    networkCall: suspend () -> ApiResponse<A>,
    saveCallResult: suspend (A) -> Unit,
    coroutineContext: CoroutineContext = Dispatchers.IO
): LiveData<Resource<T>> =
    liveData(coroutineContext) {
        emit(Resource.Loading())
        val source = localData.invoke().map { Resource.Success(it) as Resource<T> }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus is ApiResponse.Success) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus is ApiResponse.Error) {
            emit(Resource.Error(responseStatus.errorMessage))
            emitSource(source)
        }
    }

