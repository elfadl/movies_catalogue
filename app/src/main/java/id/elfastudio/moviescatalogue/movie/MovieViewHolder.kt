package id.elfastudio.moviescatalogue.movie

import androidx.recyclerview.widget.RecyclerView
import id.elfastudio.moviescatalogue.R
import id.elfastudio.moviescatalogue.core.domain.model.Movie
import id.elfastudio.moviescatalogue.core.others.Url
import id.elfastudio.moviescatalogue.databinding.ItemFilmBinding
import id.elfastudio.moviescatalogue.util.loadImage

class MovieViewHolder(private val binding: ItemFilmBinding, private val onMovieClickListener: OnMovieClickListener) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        with(binding) {
            imgPoster.loadImage("${Url.IMAGE}${movie.poster}")
            tvTitle.text = movie.getTitleAndYear()
            tvRelease.text = itemView.context.getString(R.string.release_date, movie.release)
            tvOverview.text = movie.overview
            root.setOnClickListener { onMovieClickListener.onMovieClicked(movie) }
        }
    }
}