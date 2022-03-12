package id.elfastudio.moviescatalogue.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.elfastudio.moviescatalogue.core.domain.model.Movie
import id.elfastudio.moviescatalogue.databinding.ItemFilmBinding

class FavoriteMovieAdapter(private val onMovieClickListener: OnMovieClickListener): RecyclerView.Adapter<MovieViewHolder>() {

    private val data: ArrayList<Movie> = arrayListOf()

    fun setData(movieEntities: List<Movie>){
        val diffCallback = FavoriteMovieDiffCallback(data, movieEntities)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        data.clear()
        data.addAll(movieEntities)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, onMovieClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size


}