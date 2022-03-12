package id.elfastudio.moviescatalogue.tv_show

import id.elfastudio.moviescatalogue.core.domain.model.TvShow

interface OnTvShowClickListener {

    fun onTvShowClicked(tvShow: TvShow)

}