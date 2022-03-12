package id.elfastudio.moviescatalogue.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import id.elfastudio.moviescatalogue.BaseFragment
import id.elfastudio.moviescatalogue.R
import id.elfastudio.moviescatalogue.core.domain.model.Movie
import id.elfastudio.moviescatalogue.databinding.FragmentRecyclerBinding
import id.elfastudio.moviescatalogue.paging.LoadingStateAdapter
import id.elfastudio.moviescatalogue.util.hide
import id.elfastudio.moviescatalogue.util.show
import id.elfastudio.moviescatalogue.util.withLoadStateAdapters
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalPagingApi
class MovieFragment(favorite: Boolean = false) : BaseFragment(), OnMovieClickListener {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding
    private val viewModel: MovieViewModel by viewModel()

    init {
        arguments = bundleOf(
            EXTRA_FAVORITE to favorite
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(binding?.toolbar)
        binding?.toolbar?.title = getString(R.string.movie)
        if (isFavorite()) {
            loadFavoriteData()
            binding?.toolbar?.hide()
        } else {
            hideLoading()
            fetchData()
        }
    }

    private fun loadFavoriteData() {
        val favoriteMovieAdapter = FavoriteMovieAdapter(this)
        binding?.rvMovie?.tag = "recycler_movie"
        binding?.rvMovie?.layoutManager = LinearLayoutManager(context)
        binding?.rvMovie?.adapter = favoriteMovieAdapter
        showLoading()
        viewModel.favoriteMovies.observe(viewLifecycleOwner) {
            favoriteMovieAdapter.setData(it)
            if (it.isEmpty()) {
                binding?.tvMessage?.text = getString(R.string.data_empty)
                binding?.tvMessage?.tag = "message_movie"
                hideLoading(true)
            } else {
                hideLoading()
            }
        }
    }

    private fun isFavorite(): Boolean {
        arguments?.getBoolean(EXTRA_FAVORITE)?.let { favorite ->
            return favorite
        }
        return false
    }

    private fun fetchData() {
        val movieAdapter = MovieAdapter(this)
        binding?.rvMovie?.tag = "recycler_movie"
        binding?.rvMovie?.layoutManager = LinearLayoutManager(context)
        binding?.rvMovie?.adapter = movieAdapter.withLoadStateAdapters(
            header = LoadingStateAdapter { movieAdapter.retry() },
            footer = LoadingStateAdapter { movieAdapter.retry() }
        )
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.movies.distinctUntilChanged().collectLatest {
                    movieAdapter.submitData(it)
                }
            }
        }
    }

    private fun showLoading() {
        binding?.progressBar?.show()
        binding?.tvMessage?.text = getString(R.string.lbl_loading)
        binding?.tvMessage?.show()
        binding?.rvMovie?.hide()
    }

    private fun hideLoading(error: Boolean = false) {
        binding?.progressBar?.hide()
        if (!error)
            binding?.tvMessage?.hide()
        binding?.rvMovie?.show()
    }

    override fun onMovieClicked(movie: Movie) {
        context?.let { ctx ->
            val navigateTo = MovieFragmentDirections.actionNavigationMoviesToDetailFilmActivity()
            navigateTo.movieId = movie.movieId
            binding?.root?.findNavController()?.navigate(navigateTo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_FAVORITE = "extra_favorite"
    }
}