package id.elfastudio.moviescatalogue.tv_show

import androidx.recyclerview.widget.RecyclerView
import id.elfastudio.moviescatalogue.R
import id.elfastudio.moviescatalogue.core.domain.model.TvShow
import id.elfastudio.moviescatalogue.core.others.Url
import id.elfastudio.moviescatalogue.databinding.ItemFilmBinding
import id.elfastudio.moviescatalogue.util.loadImage

class TvShowViewHolder(private val binding: ItemFilmBinding, private val onTvShowClickListener: OnTvShowClickListener) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(tvShow: TvShow) {
        with(binding) {
            imgPoster.loadImage("${Url.IMAGE}${tvShow.poster}")
            tvTitle.text = tvShow.getTitleAndYear()
            tvRelease.text = itemView.context.getString(R.string.release_date, tvShow.release)
            tvOverview.text = tvShow.overview
            root.setOnClickListener { onTvShowClickListener.onTvShowClicked(tvShow) }
        }
    }
}