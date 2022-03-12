package id.elfastudio.moviescatalogue.tv_show

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
import id.elfastudio.moviescatalogue.core.domain.model.TvShow
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
class TvShowFragment(favorite: Boolean = false) : BaseFragment(), OnTvShowClickListener {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding
    private val viewModel: TvShowViewModel by viewModel()

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
        binding?.toolbar?.title = getString(R.string.tv_show)
        if(isFavorite()){
            loadFavoriteData()
            binding?.toolbar?.hide()
        }else {
            hideLoading()
            fetchData()
        }
    }

    private fun loadFavoriteData() {
        val favoriteTvShowAdapter = FavoriteTvShowAdapter(this)
        binding?.rvMovie?.tag = "recycler_tv_show"
        binding?.rvMovie?.layoutManager = LinearLayoutManager(context)
        binding?.rvMovie?.adapter = favoriteTvShowAdapter
        showLoading()
        viewModel.favoriteTvShows.observe(viewLifecycleOwner){
            favoriteTvShowAdapter.setData(it)
            if(it.isEmpty()){
                binding?.tvMessage?.text = getString(R.string.data_empty)
                binding?.tvMessage?.tag = "message_tv_show"
                hideLoading(true)
            }else{
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
        val tvShowAdapter = TvShowAdapter(this)
        binding?.rvMovie?.tag = "recycler_tv_show"
        binding?.rvMovie?.layoutManager = LinearLayoutManager(context)
        binding?.rvMovie?.adapter = tvShowAdapter.withLoadStateAdapters(
                header = LoadingStateAdapter{tvShowAdapter.retry()},
                footer = LoadingStateAdapter{tvShowAdapter.retry()}
            )
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.tvShows.distinctUntilChanged().collectLatest {
                    tvShowAdapter.submitData(it)
                }
            }
        }
    }

    override fun onTvShowClicked(tvShow: TvShow) {
        val navigateTo = TvShowFragmentDirections.actionNavigationTvShowsToDetailFilmActivity()
        navigateTo.tvShowId = tvShow.tvShowId
        binding?.root?.findNavController()?.navigate(navigateTo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

    companion object{
        const val EXTRA_FAVORITE = "extra_favorite"
    }

}