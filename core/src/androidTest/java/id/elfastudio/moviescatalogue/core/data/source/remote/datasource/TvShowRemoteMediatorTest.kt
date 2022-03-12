package id.elfastudio.moviescatalogue.core.data.source.remote.datasource

import androidx.paging.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import id.elfastudio.moviescatalogue.core.data.source.local.entity.TVShowWithSeason
import id.elfastudio.moviescatalogue.core.data.source.local.room.AppDatabase
import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.TvShowResponse
import id.elfastudio.moviescatalogue.core.utils.DataDummy
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalPagingApi
@RunWith(MockitoJUnitRunner::class)
class TvShowRemoteMediatorTest {

    private val remote = Mockito.mock(TvShowDataSource::class.java)
    private val db: AppDatabase = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(), AppDatabase::class.java
    ).build()

    /**
     * Skenario Test :
     * 1. Menyiapkan data TvShowResponse
     * 2. Memanipulasi return dari remote.getTvShow() yang ada di dalam TvShowDataSource dengan nilai Resource.success() dengan data TvShowResponse
     * 3. Menyiapkan TvShowRemoteMediator
     * 4. Menyiapkan PagingState
     * 5. Memanggil fungsi load pada RemoteMediator
     * 6. Menguji hasil dari fungsi load yang ada di dalam RemoteMediator dengan espektasi bernilai RemoteMediator.MediatorResult.Success
     * 7. Menguji hasil dari variabel endOfPaginationReached yang ada di dalam RemoteMediator dengan espektasi bernilai false (karena data tidak kosong)
     * */
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runBlocking {
        val dummyData = DataDummy.generateTvShowResponses()
        Mockito.`when`(remote.getTvShow(Mockito.anyInt())).thenReturn(ApiResponse.Success(dummyData))
        val remoteMediator = TvShowRemoteMediator(
            remote,
            db
        )
        val pagingState = PagingState<Int, TVShowWithSeason>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    /**
     * Skenario Test :
     * 1. Memanipulasi return dari remote.getTvShow() yang ada di dalam TvShowDataSource dengan nilai Resource.success() dengan data kosong
     * 2. Menyiapkan TvShowRemoteMediator
     * 3. Menyiapkan PagingState
     * 4. Memanggil fungsi load pada RemoteMediator
     * 5. Menguji hasil dari fungsi load yang ada di dalam RemoteMediator dengan espektasi bernilai RemoteMediator.MediatorResult.Success
     * 6. Menguji hasil dari variabel endOfPaginationReached yang ada di dalam RemoteMediator dengan espektasi bernilai true (karena data kosong)
     * */
    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runBlocking {
        Mockito.`when`(remote.getTvShow(Mockito.anyInt()))
            .thenReturn(ApiResponse.Success(TvShowResponse(listOf())))
        val remoteMediator = TvShowRemoteMediator(
            remote,
            db
        )
        val pagingState = PagingState<Int, TVShowWithSeason>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    /**
     * Skenario Test :
     * 1. Memanipulasi return dari remote.getTvShow() yang ada di dalam TvShowDataSource dengan nilai Resource.error()
     * 2. Menyiapkan TvShowRemoteMediator
     * 3. Menyiapkan PagingState
     * 4. Memanggil fungsi load pada RemoteMediator
     * 5. Menguji hasil dari fungsi load yang ada di dalam RemoteMediator dengan espektasi bernilai RemoteMediator.MediatorResult.Error
     * */
    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() = runBlocking {
        Mockito.`when`(remote.getTvShow(Mockito.anyInt()))
            .thenReturn(ApiResponse.Error("Error message"))
        val remoteMediator = TvShowRemoteMediator(
            remote,
            db
        )
        val pagingState = PagingState<Int, TVShowWithSeason>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Error)
    }

}