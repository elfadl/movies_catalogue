package id.elfastudio.moviescatalogue.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.elfastudio.moviescatalogue.core.data.source.local.entity.*
import id.elfastudio.moviescatalogue.core.utils.GenreConverter

@Database(
    entities = [
        MovieEntity::class,
        FavoriteMovie::class,
        MovieRemoteKeys::class,
        TVShowEntity::class,
        FavoriteTvShow::class,
        TvShowRemoteKeys::class,
        Season::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(GenreConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeysDao(): MovieRemoteKeysDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun tvShowRemoteKeysDao(): TvShowRemoteKeysDao

    companion object {
        const val DATABASE_NAME = "movie_catalogue"
    }

}