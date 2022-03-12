package id.elfastudio.moviescatalogue.movie

import androidx.recyclerview.widget.DiffUtil
import id.elfastudio.moviescatalogue.core.domain.model.Movie

class FavoriteMovieDiffCallback(
    private val oldItem: List<Movie>,
    private val newItem: List<Movie>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItem.size

    override fun getNewListSize(): Int = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItem[oldItemPosition].movieId == newItem[newItemPosition].movieId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItem[oldItemPosition] == newItem[newItemPosition]
}