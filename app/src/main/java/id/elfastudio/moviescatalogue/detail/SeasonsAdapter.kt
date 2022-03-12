package id.elfastudio.moviescatalogue.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.elfastudio.moviescatalogue.R
import id.elfastudio.moviescatalogue.databinding.ItemSeasonsBinding
import id.elfastudio.moviescatalogue.core.data.source.local.entity.Season

class SeasonsAdapter : RecyclerView.Adapter<SeasonsAdapter.SeasonsViewHolder>() {

    private val seasons = arrayListOf<Season>()

    fun setSeasons(seasons: List<Season>?) {
        if (seasons == null) return
        this.seasons.clear()
        this.seasons.addAll(seasons)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsViewHolder {
        val binding = ItemSeasonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeasonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) {
        val season = seasons[position]
        holder.bind(season)
    }

    override fun getItemCount(): Int = seasons.size

    inner class SeasonsViewHolder(private val binding: ItemSeasonsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(season: Season) {
            val context = binding.root.context
            with(binding) {
                tvSeason.text = context.getString(R.string.season_, season.season)
                tvYearAndEpisodes.text = context.getString(
                    R.string.year_and_episodes,
                    season.year,
                    context.resources.getQuantityString(
                        R.plurals.episodes,
                        season.episode,
                        season.episode
                    )
                )
            }
        }
    }

}