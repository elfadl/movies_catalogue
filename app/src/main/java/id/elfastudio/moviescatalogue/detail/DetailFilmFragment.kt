package id.elfastudio.moviescatalogue.detail

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.elfastudio.moviescatalogue.BaseFragment
import id.elfastudio.moviescatalogue.R
import id.elfastudio.moviescatalogue.core.data.Resource
import id.elfastudio.moviescatalogue.core.domain.model.Movie
import id.elfastudio.moviescatalogue.core.domain.model.TvShow
import id.elfastudio.moviescatalogue.core.others.Url.IMAGE
import id.elfastudio.moviescatalogue.databinding.FragmentDetailFilmBinding
import id.elfastudio.moviescatalogue.util.hide
import id.elfastudio.moviescatalogue.util.loadImage
import id.elfastudio.moviescatalogue.util.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFilmFragment : BaseFragment() {

    private var _binding: FragmentDetailFilmBinding? = null
    private val binding get() = _binding
    private val viewModel: DetailFilmViewModel by viewModel()
    private var isFavorite: Boolean = false
    private var mMenu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailFilmBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(binding?.toolbar, true)
        setHasOptionsMenu(true)
        context?.let { ctx ->
            binding?.collapsingToolbar?.setCollapsedTitleTextColor(
                ContextCompat.getColor(
                    ctx,
                    R.color.white
                )
            )
            binding?.collapsingToolbar?.setExpandedTitleColor(
                ContextCompat.getColor(
                    ctx,
                    android.R.color.transparent
                )
            )
        }

        setObserver()

        val bundle = DetailFilmFragmentArgs.fromBundle(arguments as Bundle)
        val movieId = bundle.movieId
        if (movieId > 0) {
            viewModel.movieId.value = movieId
            (activity as AppCompatActivity).supportActionBar?.title =
                getString(R.string.detail_movie)
        }

        val tvShowId = bundle.tvShowId
        if (tvShowId > 0) {
            viewModel.tvShowId.value = tvShowId
            (activity as AppCompatActivity).supportActionBar?.title =
                getString(R.string.detail_tv_show)

        }
    }

    private fun setObserver() {
        viewModel.movie.observe(viewLifecycleOwner) { resource ->
            Log.i("CEHCK", "setObserver: SUCCESS ${resource is Resource.Success}")
            Log.i("CEHCK", "setObserver: SUCCESS ${resource.data}")
            when (resource) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    val movie = resource.data
                    updateUI(
                        movie?.title,
                        movie?.getTitleAndYear(),
                        movie?.release,
                        movie?.getGenres(),
                        movie?.runtime,
                        movie?.score?.toString(),
                        movie?.overview,
                        movie?.poster,
                        resource.data?.favorite == true
                    )
                    binding?.tvRelease?.visibility = View.VISIBLE
                    binding?.lblSeason?.visibility = View.GONE
                    binding?.rvSeasons?.visibility = View.GONE
                    share(movie)
                }
                is Resource.Error -> {
                    hideLoading(true)
                    binding?.tvMessage?.text = resource.message
                }
            }
        }
        viewModel.tvShow.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    val tvShow = resource.data
                    updateUI(
                        tvShow?.title,
                        tvShow?.title,
                        null,
                        tvShow?.getGenres(),
                        tvShow?.runtime,
                        tvShow?.score.toString(),
                        tvShow?.overview,
                        tvShow?.poster,
                        resource.data?.favorite == true
                    )
                    binding?.tvRelease?.visibility = View.GONE
                    binding?.lblSeason?.visibility = View.VISIBLE
                    binding?.rvSeasons?.visibility = View.VISIBLE
                    val seasonsAdapter = SeasonsAdapter()
                    seasonsAdapter.setSeasons(resource.data?.seasons?.sortedBy { season -> season.episode })
                    binding?.rvSeasons?.layoutManager =
                        LinearLayoutManager(context)
                    binding?.rvSeasons?.adapter = seasonsAdapter
                    share(tvShow)
                }
                is Resource.Error -> {
                    hideLoading(true)
                    binding?.tvMessage?.text = resource.message
                }
            }
        }
    }

    private fun updateUI(
        title: String?,
        titleAndYear: String?,
        release: String?,
        genres: String?,
        runtime: String?,
        score: String?,
        overview: String?,
        poster: String?,
        favorite: Boolean = false
    ) {
        binding?.collapsingToolbar?.title = title
        binding?.tvTitle?.text = titleAndYear
        binding?.tvRelease?.text = release
        binding?.tvGenre?.text = genres
        binding?.tvRuntime?.text = runtime
        binding?.tvScore?.text =
            getString(R.string.score_percent, score)
        binding?.tvOverview?.text = overview
        binding?.imgPoster?.loadImage(poster)
        binding?.imgPoster?.loadImage("$IMAGE${poster}")
        isFavorite = favorite
        favoriteState()
    }

    private fun favoriteState() {
        mMenu?.getItem(0)?.setIcon(
            if (isFavorite) R.drawable.ic_favorite_white else R.drawable.ic_favorite_border
        )
    }

    private fun share(data: Any?) {
        binding?.btnShare?.setOnClickListener {
            val message = when (data) {
                is Movie -> """
                    Title: ${data.title}
                    Genre: ${data.getGenres()}
                    Runtime: ${data.runtime}
                    Score: ${data.score}%
                    Release: ${data.release}
                """.trimIndent()
                is TvShow -> """
                    Title: ${data.title}
                    Genre: ${data.getGenres()}
                    Runtime: ${data.runtime}
                    Score: ${data.score}%
                """.trimIndent()
                else -> ""
            }
            val mimeType = "text/plain"
            context?.let { ctx ->
                ShareCompat.IntentBuilder(ctx)
                    .setType(mimeType)
                    .setChooserTitle(getString(R.string.share_title))
                    .setText(message)
                    .startChooser()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.favorite_menu, menu)
        this.mMenu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> binding?.root?.findNavController()?.navigateUp()
            R.id.menu_favorite -> actionFavorite(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actionFavorite(item: MenuItem) {
        isFavorite = !isFavorite
        viewModel.favoriteMovie(isFavorite)
        item.setIcon(if (isFavorite) R.drawable.ic_favorite_white else R.drawable.ic_favorite_border)
    }

    private fun showLoading() {
        binding?.progressBar?.show()
        binding?.tvMessage?.text = getString(R.string.lbl_loading)
        binding?.tvMessage?.show()
        binding?.content?.hide()
    }

    private fun hideLoading(error: Boolean = false) {
        binding?.progressBar?.hide()
        if (!error)
            binding?.tvMessage?.hide()
        binding?.content?.show()
    }

    companion object
}