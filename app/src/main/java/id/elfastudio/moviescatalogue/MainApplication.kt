package id.elfastudio.moviescatalogue

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import id.elfastudio.moviescatalogue.core.di.databaseModule
import id.elfastudio.moviescatalogue.core.di.networkModule
import id.elfastudio.moviescatalogue.core.di.repositoryModule
import id.elfastudio.moviescatalogue.di.useCaseModule
import id.elfastudio.moviescatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@ExperimentalPagingApi
class MainApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

}