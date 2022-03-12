package id.elfastudio.moviescatalogue.tv_show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import id.elfastudio.moviescatalogue.ListUpdateTestCallback
import id.elfastudio.moviescatalogue.TestCoroutineRule
import id.elfastudio.moviescatalogue.core.data.repository.TvShowRepository
import id.elfastudio.moviescatalogue.core.domain.usecase.TvShowInteractor
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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest{

    private lateinit var viewModel: FakeTvShowViewModel

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        val tvShowUseCase = TvShowInteractor(tvShowRepository)
        viewModel = FakeTvShowViewModel(tvShowUseCase)
    }

    /**
     * Skenario Testing
     * 1. Menyiapkan data List<TVShowWithSeason>
     * 2. Menyiapkan object PagingData dari data List<TVShowWithSeason>
     * 3. Memanipulasi return dari fungsi getTvShow() yang ada di dalam TvShowRepository dengan nilai Flow<PagingData>
     * 4. Mengambil nilai dari fungsi getTvShows() yang ada di dalam viewModel
     * 5. Melakukan verifikasi apakah fungsi getTvShow() pada repository sudah dipanggil
     * 6. Membuat object AsyncPagingDataDiffer bertujuan untuk mengetahui perubahan data pada PagingData
     * 7. Melakukan aksi submitData pada object AsyncPagingDataDiffer yang telah dibuat sebelumnya dengan nilai PagingData yang diambil dari fungsi getTvShows() yang ada di dalam viewModel
     * 8. Memanggil fungsi advanceUntilIdle() untuk menjalankan seluruh task yang masih pending
     * 9. Memastikan data items yang diambil dari object AsyncPagingDataDiffer sama dengan data List<TVShowWithSeason> yang telah disiapkan sebelumnya
     */
    @Test
    fun getTvShows() {
        testCoroutineRule.runBlokingTest {
            val dummyData = DataDummy.generateTvShow()
            val pagingData = PagingData.from(dummyData)
            `when`(tvShowRepository.getTvShow()).thenReturn(flowOf(pagingData))
            val result = viewModel.tvShows()
            verify(tvShowRepository).getTvShow()
            val differ = AsyncPagingDataDiffer(
                diffCallback = TvShowDiffCallback(),
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
     * 1. Menyiapkan data TVShow
     * 2. Menyiapkan data List<FavoriteTvShowWithTvShow>
     * 3. Memanipulasi return dari fungsi getFavoriteTvShows() yang ada di dalam FakeTvShowRepository bernilai LiveData<List<FavoriteTvShowWithTvShow>>
     * 4. Melakukan observe pada fungsi getFavoriteTvShows() yang ada di dalam viewModel dengan bantuan LiveDataTestUtil
     * 5. Melakukan verifikasi apakah fungsi getFavoriteTvShows() yang ada di dalam FakeTvShowRepository terpanggil
     * 6. Memastikan hasil observe dari fungsi getFavoriteTvShows() yang ada di dalam viewModel sama dengan data List<FavoriteTvShowWithTvShow> yang telah disiapkan sebelumnya
     * */
    @Test
    fun getFavoriteTvShows(){
        testCoroutineRule.runBlokingTest {
            val dataDummy = DataDummy.generateTvShow()
            `when`(tvShowRepository.getFavoriteTvShows()).thenReturn(flowOf(dataDummy))
            val result = viewModel.favoriteTvShows().first()
            verify(tvShowRepository).getFavoriteTvShows()
            assertEquals(dataDummy, result)
        }
    }

}