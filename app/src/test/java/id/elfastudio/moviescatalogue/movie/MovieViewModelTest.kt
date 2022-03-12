package id.elfastudio.moviescatalogue.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import id.elfastudio.moviescatalogue.ListUpdateTestCallback
import id.elfastudio.moviescatalogue.TestCoroutineRule
import id.elfastudio.moviescatalogue.core.data.repository.MovieRepository
import id.elfastudio.moviescatalogue.core.domain.usecase.MovieInteractor
import id.elfastudio.moviescatalogue.core.utils.DataDummy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest{

    private lateinit var viewModel: FakeMovieViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        val movieUseCase = MovieInteractor(movieRepository)
        viewModel = FakeMovieViewModel(movieUseCase)
    }

    /**
     * Skenario Testing
     * 1. Menyiapkan data List<Movie>
     * 2. Menyiapkan object PagingData dari data List<Movie>
     * 3. Memanipulasi return dari fungsi getMovie() yang ada di dalam MovieRepository dengan nilai Flow<PagingData>
     * 4. Mengambil nilai dari fungsi getMovies() yang ada di dalam viewModel
     * 5. Melakukan verifikasi apakah fungsi getMovie() pada repository sudah dipanggil
     * 6. Membuat object AsyncPagingDataDiffer bertujuan untuk mengetahui perubahan data pada PagingData
     * 7. Melakukan aksi submitData pada object AsyncPagingDataDiffer yang telah dibuat sebelumnya dengan nilai PagingData yang diambil dari fungsi getMovies() yang ada di dalam viewModel
     * 8. Memanggil fungsi advanceUntilIdle() untuk menjalankan seluruh task yang masih pending
     * 9. Memastikan data items yang diambil dari object AsyncPagingDataDiffer sama dengan data List<Movie> yang telah disiapkan sebelumnya
     */
    @Test
    fun getMoviesSuccess() {
        testCoroutineRule.runBlokingTest {
            val dummyData = DataDummy.generateMovies()
            Mockito.`when`(movieRepository.getMovie()).thenReturn(flowOf(PagingData.from(dummyData)))
            val result = viewModel.movies()
            val differ = AsyncPagingDataDiffer(
                diffCallback = MovieDiffCallback(),
                updateCallback = ListUpdateTestCallback(),
                workerDispatcher = Dispatchers.Main
            )
            differ.submitData(result.first())
            advanceUntilIdle()
            assertEquals(dummyData, differ.snapshot().items)
        }
    }

    /**
     * Skenario Testting
     * 1. Menyiapkan data Movie
     * 2. Menyiapkan data List<FavoriteMovieWithMovie>
     * 3. Memanipulasi return dari fungsi getFavoriteMovies() yang ada di dalam FakeMovieRepository bernilai LiveData<List<FavoriteMovieWithMovie>>
     * 4. Melakukan observe pada fungsi getFavoriteMovies() yang ada di dalam viewModel dengan bantuan LiveDataTestUtil
     * 5. Melakukan verifikasi apakah fungsi getFavoriteMovies() yang ada di dalam FakeMovieRepository terpanggil
     * 6. Memastikan hasil observe dari fungsi getFavoriteMovies() yang ada di dalam viewModel sama dengan data List<FavoriteMovieWithMovie> yang telah disiapkan sebelumnya
     * */
    @Test
    fun getFavoriteMovies(){
        testCoroutineRule.runBlokingTest {
            val dataDummy = DataDummy.generateMovies()
            Mockito.`when`(movieRepository.getFavoriteMovies()).thenReturn(flowOf(dataDummy))
            val result = viewModel.favoriteMovies().first()
            verify(movieRepository).getFavoriteMovies()
            assertEquals(dataDummy, result)
        }
    }

}