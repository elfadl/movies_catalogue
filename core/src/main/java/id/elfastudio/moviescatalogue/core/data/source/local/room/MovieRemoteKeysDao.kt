package id.elfastudio.moviescatalogue.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.elfastudio.moviescatalogue.core.data.source.local.entity.MovieRemoteKeys
import id.elfastudio.moviescatalogue.core.data.source.local.entity.MovieRemoteKeys.Companion.TABLE_NAME

@Dao
interface MovieRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKey: List<MovieRemoteKeys>): List<Long>

    @Query("SELECT * FROM $TABLE_NAME WHERE repoId = :id")
    suspend fun remoteKeysMovieId(id: Int): MovieRemoteKeys?

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clearMovieRemoteKeys()

}