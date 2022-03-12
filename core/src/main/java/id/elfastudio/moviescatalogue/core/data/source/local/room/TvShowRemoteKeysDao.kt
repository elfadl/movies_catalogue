package id.elfastudio.moviescatalogue.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.elfastudio.moviescatalogue.core.data.source.local.entity.TvShowRemoteKeys
import id.elfastudio.moviescatalogue.core.data.source.local.entity.TvShowRemoteKeys.Companion.TABLE_NAME

@Dao
interface TvShowRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKey: List<TvShowRemoteKeys>): List<Long>

    @Query("SELECT * FROM $TABLE_NAME WHERE repoId = :id")
    suspend fun remoteKeysTvShowId(id: Int): TvShowRemoteKeys?

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clearTvShowRemoteKeys()

}