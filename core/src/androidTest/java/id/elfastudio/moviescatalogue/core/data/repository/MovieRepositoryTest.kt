package id.elfastudio.moviescatalogue.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteMovie
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteMovieWithMovie
import id.elfastudio.moviescatalogue.core.data.source.local.entity.MovieEntity
import id.elfastudio.moviescatalogue.core.data.source.local.room.AppDatabase
import id.elfastudio.moviescatalogue.core.data.source.remote.datasource.MovieDataSource
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
 * Untuk test pada Paging, dibedakan ke MovieRemoteMediatorTest karena crash dengan InstantTaskExecutorRule()
 * */
@ExperimentalCoroutinesApi
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val remote = mock(MovieDataSource::class.java)
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
     * 1. Menyiapkan data Movie
     * 2. Menyiapkan data DetailMovieResponse
     * 3. Memanipulasi return dari fungsi getDetailMovie() yang ada di dalam MovieDataSource dengan nilai Resource.success()
     * 4. Malakukan pemanggilan terhadap fungsi getDetailMovie() yang ada di dalam MovieDataSource kemudian dimapping menjadi data Movie dan Favorite yang kemudian keduanya diinsert ke dalam database
     * 5. Mengambil data dari database melalui movieDao().getDetailMovie()
     * 6. Memastikan data pada movieDao().getDetailMovie() bernilai tidak null
     * 7. Memastikan data pada movieDao().getDetailMovie() bernilai FavoriteMovieWithMovie
     * */
    @Test
    fun getDetailMovie() {
        testCoroutineRule.runBlokingTest {
            val movie = DataDummy.generateMovieEntities()[0]
            val detailMovie = DataDummy.generateDetailMovieResponse()
            `when`(remote.getDetailMovie(detailMovie.id))
                .thenReturn(ApiResponse.Success(detailMovie))
            val response = remote.getDetailMovie(detailMovie.id)
            if (response is ApiResponse.Success) {
                response.data.let {
                    val data = MovieEntity(
                        it.id,
                        it.title,
                        it.genres.map { genre -> genre.name },
                        it.getRelease(),
                        it.getRuntime(),
                        it.getScore(),
                        it.overview,
                        it.posterPath,
                        it.popularity
                    )
                    val favorite = FavoriteMovie(it.id, false)
                    mockDb.movieDao().insert(listOf(data))
                    mockDb.movieDao().insertFavoriteMovie(listOf(favorite))
                }
            }
            val detailMovieSuccess = mockDb.movieDao().getDetailMovie(detailMovie.id).first()
            assertNotNull(detailMovieSuccess)
            assertEquals(
                FavoriteMovieWithMovie(
                    movie,
                    FavoriteMovie(movie.movieId, false)
                ), detailMovieSuccess
            )
        }
    }

    /**
     * Skenario Test:
     * 1. Menyiapkan data FavoriteMovie
     * 2. Menyiapkan data ekspektasi FavoriteMovieWithMovie
     * 3. Menyiapkan data Movie
     * 4. Melakukan insert data Movie ke database
     * 5. Melakukan insert data FavoriteMovie ke database
     * 6. Mengambil data FavoriteMovieWithMovie dari database
     * 7. Memastikan data FavoriteMovieWithMovie dari database tidak bernilai null
     * 8. Memastikan data FavoriteMovieWithMovie dari database bernilai data ekspektasi FavoriteMovieWithMovie
     * */
    @Test
    fun getFavoriteMovies() {
        runBlocking {
            val dummyFavoriteMovies = DataDummy.generateMovieEntities().map {
                FavoriteMovie(it.movieId, true)
            }
            val expectFavoriteMovies = DataDummy.generateMovieEntities().map {
                FavoriteMovieWithMovie(it, FavoriteMovie(it.movieId, true))
            }
            val dummyMovies = DataDummy.generateMovieEntities()
            mockDb.movieDao().insert(dummyMovies)
            mockDb.movieDao().insertFavoriteMovie(dummyFavoriteMovies)
            val favoriteMovies = mockDb.movieDao().getFavoriteMovies().first()
            assertNotNull(favoriteMovies)
            assertEquals(expectFavoriteMovies, favoriteMovies)
        }
    }

    /**
     * Skenario Test:
     * 1. Menyiapkan data Movie
     * 2. Menyiapkan data FavoriteMovie
     * 3. Malakukan insert data FavoriteMovie ke dalam database
     * 4. Mengambil data FavoriteMovie dari database
     * 5. Memastikan data FavoriteMovie dari database sesuai ekspektasi
     * 6. Memastikan variable favorite pada data FavoriteMovie dari database bernilai true
     * */
    @Test
    fun favoriteMovie() {
        testCoroutineRule.runBlokingTest {
            val dummyMovie = DataDummy.generateMovies()[0]
            val favoriteMovie = FavoriteMovie(dummyMovie.movieId, true)
            mockDb.movieDao().insertFavoriteMovie(listOf(favoriteMovie))
            val result = mockDb.movieDao().getFavoriteMovie(dummyMovie.movieId).first()
            assertEquals(favoriteMovie, result)
            assertEquals(true, result.favorite)
        }
    }
}