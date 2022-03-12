package id.elfastudio.moviescatalogue.di

import id.elfastudio.moviescatalogue.core.domain.usecase.MovieInteractor
import id.elfastudio.moviescatalogue.core.domain.usecase.MovieUseCase
import id.elfastudio.moviescatalogue.core.domain.usecase.TvShowInteractor
import id.elfastudio.moviescatalogue.core.domain.usecase.TvShowUseCase
import id.elfastudio.moviescatalogue.detail.DetailFilmViewModel
import id.elfastudio.moviescatalogue.movie.MovieViewModel
import id.elfastudio.moviescatalogue.tv_show.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TvShowUseCase> { TvShowInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailFilmViewModel(get(), get()) }
}