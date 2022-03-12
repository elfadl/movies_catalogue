package id.elfastudio.moviescatalogue.tv_show

import androidx.recyclerview.widget.DiffUtil
import id.elfastudio.moviescatalogue.core.domain.model.TvShow

class FavoriteTvShowDiffCallback(
    private val oldItem: List<TvShow>,
    private val newItem: List<TvShow>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItem.size

    override fun getNewListSize(): Int = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItem[oldItemPosition].tvShowId == newItem[newItemPosition].tvShowId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItem[oldItemPosition] == newItem[newItemPosition]
}