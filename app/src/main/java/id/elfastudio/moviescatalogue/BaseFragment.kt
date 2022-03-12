package id.elfastudio.moviescatalogue

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

open class BaseFragment : Fragment() {

    protected fun setToolbar(
        toolbar: Toolbar?,
        enableOnBackPressed: Boolean = false
    ) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
            enableOnBackPressed
        )
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        context?.let { ctx ->
            toolbar?.setTitleTextColor(ContextCompat.getColor(ctx, R.color.white))
        }
        toolbar?.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
    }

}