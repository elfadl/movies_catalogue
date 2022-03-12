package id.elfastudio.moviescatalogue.core.data.source.remote.datasource

import androidx.paging.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import id.elfastudio.moviescatalogue.core.data.source.local.entity.MovieEntity
import id.elfastudio.moviescatalogue.core.data.source.local.room.AppDatabase
import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.MovieResponse
import id.elfastudio.moviescatalogue.core.utils.DataDummy
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalPagingApi
@RunWith(MockitoJUnitRunner::class)
class MovieRemoteMediatorTest{

    private val remote = mock(MovieDataSource::class.java)
    private val db: AppDatabase = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(), AppDatabase::class.java).build()

    /**
     * Skenario Test :
     * 1. Menyiapkan data MovieResponse
     * 2. Memanipulasi return dari remote.getMovie() yang ada di dalam MovieDataSource dengan nilai Resource.success() dengan data MovieResponse
     * 3. Menyiapkan MovieRemoteMediator
     * 4. Menyiapkan PagingState
     * 5. Memanggil fungsi load pada RemoteMediator
     * 6. Menguji hasil dari fungsi load yang ada di dalam RemoteMediator dengan espektasi bernilai RemoteMediator.MediatorResult.Success
     * 7. Menguji hasil dari variabel endOfPaginationReached yang ada di dalam RemoteMediator dengan espektasi bernilai false (karena data tidak kosong)
     * */
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runBlocking{
        val dummyData = DataDummy.generateMovieResponses()
        Mockito.`when`(remote.getMovie(Mockito.anyInt())).thenReturn(flowOf(ApiResponse.Success(dummyData)))
        val remoteMediator = MovieRemoteMediator(
            remote,
            db
        )
        val pagingState = PagingState<Int, MovieEntity>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        Assert.assertTrue(result is RemoteMediator.MediatorResult.Success)
        Assert.assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    /**
     * Skenario Test :
     * 1. Memanipulasi return dari remote.getMovie() yang ada di dalam MovieDataSource dengan nilai Resource.success() dengan data kosong
     * 2. Menyiapkan MovieRemoteMediator
     * 3. Menyiapkan PagingState
     * 4. Memanggil fungsi load pada RemoteMediator
     * 5. Menguji hasil dari fungsi load yang ada di dalam RemoteMediator dengan espektasi bernilai RemoteMediator.MediatorResult.Success
     * 6. Menguji hasil dari variabel endOfPaginationReached yang ada di dalam RemoteMediator dengan espektasi bernilai true (karena data kosong)
     * */
    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runBlocking {
        Mockito.`when`(remote.getMovie(Mockito.anyInt()))
            .thenReturn(flowOf(ApiResponse.Success(MovieResponse(listOf()))))
        val remoteMediator = MovieRemoteMediator(
            remote,
            db
        )
        val pagingState = PagingState<Int, MovieEntity>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        Assert.assertTrue(result is RemoteMediator.MediatorResult.Success)
        Assert.assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    /**
     * Skenario Test :
     * 1. Memanipulasi return dari remote.getMovie() yang ada di dalam MovieDataSource dengan nilai Resource.error()
     * 2. Menyiapkan MovieRemoteMediator
     * 3. Menyiapkan PagingState
     * 4. Memanggil fungsi load pada RemoteMediator
     * 5. Menguji hasil dari fungsi load yang ada di dalam RemoteMediator dengan espektasi bernilai RemoteMediator.MediatorResult.Error
     * */
    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() = runBlocking {
        Mockito.`when`(remote.getMovie(Mockito.anyInt()))
            .thenReturn(flowOf(ApiResponse.Error("Error message")))
        val remoteMediator = MovieRemoteMediator(
            remote,
            db
        )
        val pagingState = PagingState<Int, MovieEntity>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        Assert.assertEquals(true, result is RemoteMediator.MediatorResult.Error)
    }

}