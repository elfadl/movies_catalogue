package id.elfastudio.moviescatalogue.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import id.elfastudio.moviescatalogue.LiveDataTestUtil
import id.elfastudio.moviescatalogue.TestCoroutineRule
import id.elfastudio.moviescatalogue.core.data.Resource
import id.elfastudio.moviescatalogue.core.data.repository.MovieRepository
import id.elfastudio.moviescatalogue.core.data.repository.TvShowRepository
import id.elfastudio.moviescatalogue.core.data.source.remote.response.DetailMovieResponse
import id.elfastudio.moviescatalogue.core.data.source.remote.response.DetailTvShowResponse
import id.elfastudio.moviescatalogue.core.domain.usecase.MovieInteractor
import id.elfastudio.moviescatalogue.core.domain.usecase.TvShowInteractor
import id.elfastudio.moviescatalogue.core.utils.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailFilmViewModelTest{

    private lateinit var viewModel: FakeDetailFilmViewModel
    private val errorMessage = "Error Message"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Before
    fun setUp() {
        val movieUseCase = MovieInteractor(movieRepository)
        val tvShowUseCase = TvShowInteractor(tvShowRepository)
        viewModel = FakeDetailFilmViewModel(movieUseCase, tvShowUseCase)
    }

    /**
     * Skenario Testing
     * 1. Menyiapkan data MovieResponse
     * 2. Menyiapkan data Movie
     * 3. Memanipulasi return dari fungsi getDetailMovie() yang ada di dalam MovieRepository dengan nilai LiveData Resource.success
     * 4. Memberikan nilai pada movieId yang ada di dalam viewModel
     * 5. Melakukan observe pada viewModel.getMovie() dengan bantuan LiveDataTestUtil
     * 6. Melakukan verifikasi apakah fungsi getDetailMovie() pada repository sudah dipanggil
     * 7. Memastikan hasil observe dari getMovie() apakah bernilai sama dengan data Movie yang sudah disiapkan sebelumnya
     */
    @Test
    fun getMovieSuccess() {
        testCoroutineRule.runBlokingTest {
            val detailMovieResponse = DataDummy.generateDetailMovieResponse()
            val detailMovie = DataDummy.generateMovies()[0]
            doReturn(MutableLiveData(Resource.Success(detailMovie)))
                .`when`(movieRepository)
                .getDetailMovie(detailMovieResponse.id)
            viewModel.movieId.value = detailMovieResponse.id
            val movies = LiveDataTestUtil.getValue(viewModel.movie())
            verify(movieRepository).getDetailMovie(detailMovieResponse.id)
            assertEquals(detailMovie, movies.data)
        }
    }

    /**
     * Skenario Testing
     * 1. Menyiapkan data DetailMovieResponse
     * 2. Memanipulasi return dari fungsi getDetailMovie() yang ada di dalam MovieRepository dengan nilai LiveData Resource.error()
     * 3. Memberikan nilai pada movieId yang ada di dalam viewModel
     * 4. Melakukan observe pada viewModel.getMovie() dengan bantuan LiveDataTestUtil
     * 5. Melakukan verifikasi apakah fungsi getDetailMovie() pada repository sudah dipanggil
     * 6. Memastikan hasil observe dari getMovie() apakah bernilai Resource.error
     */
    @Test
    fun getMovieError() {
        testCoroutineRule.runBlokingTest {
            val detailMovieResponse = DataDummy.generateDetailMovieResponse()
            doReturn(MutableLiveData(Resource.Error<DetailMovieResponse>(errorMessage)))
                .`when`(movieRepository)
                .getDetailMovie(detailMovieResponse.id)
            viewModel.movieId.value = detailMovieResponse.id
            val movies = LiveDataTestUtil.getValue(viewModel.movie())
            verify(movieRepository).getDetailMovie(detailMovieResponse.id)
            assertEquals(errorMessage, movies.message)
        }
    }

    /**
     * Skenario Testing
     * 1. Menyiapkan data TvShowResponse
     * 2. Menyiapkan data TVShow
     * 3. Menyiapkan data Resource.success()
     * 4. Memanipulasi return dari fungsi getDetailTvShow() yang ada di dalam TvShowRepository dengan nilai LiveData Resource.success()
     * 5. Memberikan nilai pada tvShowId yang ada di dalam viewModel
     * 6. Melakukan observe pada viewModel.getTvShow() dengan bantuan LiveDataTestUtil
     * 7. Melakukan verifikasi apakah fungsi getDetailTvShow() pada repository sudah dipanggil
     * 8. Memastikan hasil observe dari getTvShow() apakah bernilai sama dengan data TvShow yang sudah disiapkan sebelumnya
     */
    @Test
    fun getTvShowsSuccess() {
        testCoroutineRule.runBlokingTest {
            val detailTvShowResponse = DataDummy.generateDetailTvSHowResponse()
            val detailTvShow = DataDummy.generateTvShow()[0]
            val data = Resource.Success(detailTvShow)
            doReturn(MutableLiveData(data))
                .`when`(tvShowRepository)
                .getDetailTvShow(detailTvShowResponse.id)
            viewModel.tvShowId.value = detailTvShowResponse.id
            val tvShows = LiveDataTestUtil.getValue(viewModel.tvShow())
            verify(tvShowRepository).getDetailTvShow(detailTvShowResponse.id)
            assertEquals(detailTvShow, tvShows.data)
        }
    }

    /**
     * Skenario Testing
     * 1. Menyiapkan data DetailTvShowResponse
     * 2. Memanipulasi return dari fungsi getDetailTvShow() yang ada di dalam TvShowRepository dengan nilai LiveData Resource.error()
     * 3. Memberikan nilai pada tvShowId yang ada di dalam viewModel
     * 4. Melakukan observe pada viewModel.getTvShow() dengan bantuan LiveDataTestUtil
     * 5. Melakukan verifikasi apakah fungsi getDetailTvShow() pada repository sudah dipanggil
     * 6. Memastikan hasil observe dari getTvShow() apakah bernilai Resource.error()
     */
    @Test
    fun getTvShowsError() {
        testCoroutineRule.runBlokingTest {
            val detailTvShowResponse = DataDummy.generateDetailTvSHowResponse()
            doReturn(MutableLiveData(Resource.Error<DetailTvShowResponse>(errorMessage)))
                .`when`(tvShowRepository)
                .getDetailTvShow(detailTvShowResponse.id)
            viewModel.tvShowId.value = detailTvShowResponse.id
            val tvShows = LiveDataTestUtil.getValue(viewModel.tvShow())
            verify(tvShowRepository).getDetailTvShow(detailTvShowResponse.id)
            assertEquals(errorMessage, tvShows.message)
        }
    }

}