package id.elfastudio.moviescatalogue.movie

import androidx.recyclerview.widget.DiffUtil
import id.elfastudio.moviescatalogue.core.domain.model.Movie

class MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.movieId == newItem.movieId

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}