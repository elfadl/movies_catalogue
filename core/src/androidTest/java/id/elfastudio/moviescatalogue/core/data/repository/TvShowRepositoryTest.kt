package id.elfastudio.moviescatalogue.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import id.elfastudio.moviescatalogue.core.data.source.local.entity.*
import id.elfastudio.moviescatalogue.core.data.source.local.room.AppDatabase
import id.elfastudio.moviescatalogue.core.data.source.remote.datasource.TvShowDataSource
import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiResponse
import id.elfastudio.moviescatalogue.core.utils.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Catatan:
 * Untuk test pada Paging, dibedakan ke TvShowRemoteMediatorTest karena crash dengan InstantTaskExecutorRule()
 * */
@ExperimentalCoroutinesApi
class TvShowRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val remote = mock(TvShowDataSource::class.java)
    private val mockDb = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(), AppDatabase::class.java
    ).build()

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mockDb.close()
    }

    /**
     * Skenario Test:
     * 1. Menyiapkan data TVShow
     * 2. Menyiapkan data DetailTvShowResponse
     * 3. Memanipulasi return dari fungsi getDetailTvShow() yang ada di dalam TvShowDataSource dengan nilai Resource.success()
     * 4. Malakukan pemanggilan terhadap fungsi getDetailTvShow() yang ada di dalam TvShowDataSource kemudian dimapping menjadi data TVShow dan Favorite yang kemudian keduanya diinsert ke dalam database
     * 5. Mengambil data dari database melalui movieDao().getDetailTvShow()
     * 6. Memastikan data pada movieDao().getDetailTvShow() bernilai tidak null
     * 7. Memastikan data pada movieDao().getDetailTvShow() bernilai FavoriteTvShowWithTvShow
     * */
    @Test
    fun getDetailTvShow() {
        testCoroutineRule.runBlokingTest {
            val tvShow = DataDummy.generateTvShowEntities()[0]
            val detailTvShow = DataDummy.generateDetailTvSHowResponse()
            val seasons = detailTvShow.seasons.map { item ->
                Season(
                    item.id,
                    item.seasonNumber,
                    item.airDate?.split("-")?.get(0) ?: "-",
                    item.episodeCount,
                    detailTvShow.id
                )
            }
            `when`(remote.getDetailTvShow(detailTvShow.id))
                .thenReturn(ApiResponse.Success(detailTvShow))
            val response = remote.getDetailTvShow(detailTvShow.id)
            if (response is ApiResponse.Success) {
                response.data.let {
                    val data = TVShowEntity(
                        it.id,
                        it.name,
                        it.genres.map { genre -> genre.name },
                        it.getRelease(),
                        it.getRuntime(),
                        it.getScore(),
                        it.overview,
                        it.posterPath,
                        it.popularity
                    )
                    val seasons = it.seasons.map { item ->
                        Season(
                            item.id,
                            item.seasonNumber,
                            item.airDate?.split("-")?.get(0) ?: "-",
                            item.episodeCount,
                            data.tvShowId
                        )
                    }
                    val favorite = FavoriteTvShow(it.id, false)
                    mockDb.tvShowDao().insertTVShow(listOf(data))
                    mockDb.tvShowDao().insertSeasons(seasons)
                    mockDb.tvShowDao().insertFavoriteTvShow(listOf(favorite))
                }
            }
            val detailTvShowSuccess = mockDb.tvShowDao().getDetailTvShow(detailTvShow.id).first()
            assertNotNull(detailTvShowSuccess)
            assertEquals(
                FavoriteTvShowWithTvShow(
                    TVShowWithSeason(tvShow, seasons),
                    FavoriteTvShow(tvShow.tvShowId, false)
                ), detailTvShowSuccess
            )
        }
    }

    /**
     * Skenario Test:
     * 1. Menyiapkan data FavoriteTvShow
     * 2. Menyiapkan data ekspektasi FavoriteTvShowWithTvShow
     * 3. Menyiapkan data TVShow
     * 4. Melakukan insert data TVShow ke database
     * 5. Melakukan insert data FavoriteTvShow ke database
     * 6. Mengambil data FavoriteTvShowWithTvShow dari database
     * 7. Memastikan data FavoriteTvShowWithTvShow dari database tidak bernilai null
     * 8. Memastikan data FavoriteTvShowWithTvShow dari database bernilai data ekspektasi FavoriteTvShowWithTvShow
     * */
    @Test
    fun getFavoriteTvShows() {
        runBlocking {
            val dummyFavoriteTvShows = DataDummy.generateTvShowEntities().map {
                FavoriteTvShow(it.tvShowId, true)
            }
            val expectFavoriteTvShows = DataDummy.generateTvShowEntities().map {
                FavoriteTvShowWithTvShow(
                    TVShowWithSeason(it, listOf()),
                    FavoriteTvShow(it.tvShowId, true)
                )
            }
            val dummyTvShows = DataDummy.generateTvShowEntities()
            mockDb.tvShowDao().insertTVShow(dummyTvShows)
            mockDb.tvShowDao().insertFavoriteTvShow(dummyFavoriteTvShows)
            val favoriteTvShows = mockDb.tvShowDao().getFavoriteTvShows().first()
            assertNotNull(favoriteTvShows)
            assertEquals(expectFavoriteTvShows, favoriteTvShows)
        }
    }

    /**
     * Skenario Test:
     * 1. Menyiapkan data TVShow
     * 2. Menyiapkan data FavoriteTvShow
     * 3. Malakukan insert data FavoriteTvShow ke dalam database
     * 4. Mengambil data FavoriteTvShow dari database
     * 5. Memastikan data FavoriteTvShow dari database sesuai ekspektasi
     * 6. Memastikan variable favorite pada data FavoriteTvShow dari database bernilai true
     * */
    @Test
    fun favoriteTvShow() {
        testCoroutineRule.runBlokingTest {
            val dummyTvShow = DataDummy.generateTvShow()[0]
            val favoriteTvShow = FavoriteTvShow(dummyTvShow.tvShowId, true)
            mockDb.tvShowDao().insertFavoriteTvShow(listOf(favoriteTvShow))
            val result = mockDb.tvShowDao().getFavoriteTvShow(dummyTvShow.tvShowId).first()
            assertEquals(favoriteTvShow, result)
            assertEquals(true, result.favorite)
        }
    }
}