package id.elfastudio.moviescatalogue.core.di

import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import id.elfastudio.moviescatalogue.core.BuildConfig
import id.elfastudio.moviescatalogue.core.data.repository.MovieRepository
import id.elfastudio.moviescatalogue.core.data.repository.TvShowRepository
import id.elfastudio.moviescatalogue.core.data.source.local.room.AppDatabase
import id.elfastudio.moviescatalogue.core.data.source.remote.datasource.MovieDataSource
import id.elfastudio.moviescatalogue.core.data.source.remote.datasource.MovieRemoteMediator
import id.elfastudio.moviescatalogue.core.data.source.remote.datasource.TvShowDataSource
import id.elfastudio.moviescatalogue.core.data.source.remote.datasource.TvShowRemoteMediator
import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiHelper
import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiHelperImpl
import id.elfastudio.moviescatalogue.core.data.source.remote.network.ApiService
import id.elfastudio.moviescatalogue.core.domain.repository.IMovieRepository
import id.elfastudio.moviescatalogue.core.domain.repository.ITvShowRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val databaseModule = module {
    factory { get<AppDatabase>().movieDao() }
    factory { get<AppDatabase>().movieRemoteKeysDao() }
    factory { get<AppDatabase>().tvShowDao() }
    factory { get<AppDatabase>().tvShowRemoteKeysDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("kamal.elfadl".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url =
                    originalHttpUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY)
                        .build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
    single<ApiHelper> { ApiHelperImpl(get()) }
}

@ExperimentalPagingApi
val repositoryModule = module {
    single { MovieDataSource(get()) }
    single { TvShowDataSource(get()) }
    single { MovieRemoteMediator(get(), get()) }
    single { TvShowRemoteMediator(get(), get()) }
    single<IMovieRepository> { MovieRepository(get(), get()) }
    single<ITvShowRepository> { TvShowRepository(get(), get()) }
}