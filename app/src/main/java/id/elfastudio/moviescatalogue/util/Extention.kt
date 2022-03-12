package id.elfastudio.moviescatalogue.util

import android.view.View
import android.widget.ImageView
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.elfastudio.moviescatalogue.R

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .apply (
            RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
        )
        .centerCrop()
        .into(this)
}

fun View.hide(){
    visibility = View.GONE
}

fun View.show(){
    visibility = View.VISIBLE
}

fun <T: Any, V: RecyclerView.ViewHolder> PagingDataAdapter<T, V>.withLoadStateAdapters(
    header: LoadStateAdapter<*>,
    footer: LoadStateAdapter<*>
): ConcatAdapter {
    addLoadStateListener { loadStates ->
        header.loadState = loadStates.refresh
        footer.loadState = loadStates.append
    }
    return ConcatAdapter(header, this, footer)
}