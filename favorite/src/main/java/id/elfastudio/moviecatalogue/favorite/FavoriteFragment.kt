package id.elfastudio.moviecatalogue.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.ExperimentalPagingApi
import com.google.android.material.tabs.TabLayoutMediator
import id.elfastudio.moviecatalogue.favorite.databinding.FragmentFavoriteBinding
import id.elfastudio.moviescatalogue.BaseFragment
import id.elfastudio.moviescatalogue.R

@ExperimentalPagingApi
class FavoriteFragment : BaseFragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(binding?.toolbar)
        binding?.toolbar?.title = getString(R.string.title_favorite)
        val sectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.let {
            TabLayoutMediator(it.tabs, it.viewPager){ tab, position ->
                tab.text = getString(SectionsPagerAdapter.TAB_TITLES[position])
            }.attach()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}