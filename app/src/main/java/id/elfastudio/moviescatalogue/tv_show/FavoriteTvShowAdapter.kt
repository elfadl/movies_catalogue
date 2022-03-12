package id.elfastudio.moviescatalogue.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.elfastudio.moviescatalogue.core.domain.model.TvShow
import id.elfastudio.moviescatalogue.databinding.ItemFilmBinding

class FavoriteTvShowAdapter(private val onTvShowClickListener: OnTvShowClickListener) :
    RecyclerView.Adapter<TvShowViewHolder>() {

    private val data: ArrayList<TvShow> = arrayListOf()

    fun setData(tvShowEntities: List<TvShow>) {
        val diffCallback = FavoriteTvShowDiffCallback(data, tvShowEntities)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        data.clear()
        data.addAll(tvShowEntities)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding, onTvShowClickListener)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size


}