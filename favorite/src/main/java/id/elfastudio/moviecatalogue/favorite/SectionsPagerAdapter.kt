package id.elfastudio.moviecatalogue.favorite

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.paging.ExperimentalPagingApi
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.elfastudio.moviescatalogue.R
import id.elfastudio.moviescatalogue.movie.MovieFragment
import id.elfastudio.moviescatalogue.tv_show.TvShowFragment


@ExperimentalPagingApi
class SectionsPagerAdapter(fm: FragmentManager, lifeCycle: Lifecycle) :
    FragmentStateAdapter(fm, lifeCycle) {

    override fun getItemCount(): Int = TAB_TITLES.size

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment(true)
            1 -> TvShowFragment(true)
            else -> Fragment()
        }

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(R.string.movie, R.string.tv_show)
    }
}