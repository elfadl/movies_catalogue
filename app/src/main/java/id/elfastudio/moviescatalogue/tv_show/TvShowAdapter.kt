package id.elfastudio.moviescatalogue.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import id.elfastudio.moviescatalogue.core.domain.model.TvShow
import id.elfastudio.moviescatalogue.databinding.ItemFilmBinding

class TvShowAdapter(private val onTvShowClickListener: OnTvShowClickListener) :
    PagingDataAdapter<TvShow, TvShowViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding, onTvShowClickListener)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object{
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
                oldItem.tvShowId == newItem.tvShowId

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
                oldItem == newItem

        }
    }

}