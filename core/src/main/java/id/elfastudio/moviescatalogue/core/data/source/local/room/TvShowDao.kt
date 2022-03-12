package id.elfastudio.moviescatalogue.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.*
import id.elfastudio.moviescatalogue.core.data.source.local.entity.*
import id.elfastudio.moviescatalogue.core.data.source.local.entity.TVShowEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteTvShow.Companion.TABLE_NAME as FAVORITE_TABLE

@Dao
interface TvShowDao {

    @Transaction
    @Query("SELECT * FROM $TABLE_NAME ORDER BY popularity DESC")
    fun getTvShows(): PagingSource<Int, TVShowWithSeason>

    @Query("SELECT * FROM $FAVORITE_TABLE WHERE tv_show_id = :tvShowId")
    fun getFavoriteTvShow(tvShowId: Int): Flow<FavoriteTvShow>

    @Transaction
    @Query("SELECT * FROM $FAVORITE_TABLE LEFT JOIN $TABLE_NAME ON $FAVORITE_TABLE.tv_show_id = $TABLE_NAME.tv_show_id WHERE favorite = 1 ORDER BY popularity DESC")
    fun getFavoriteTvShows(): Flow<List<FavoriteTvShowWithTvShow>>

    @Query("SELECT * FROM $TABLE_NAME WHERE tv_show_id = :tvShowId LIMIT 1")
    fun getDetailTvShow(tvShowId: Int): Flow<FavoriteTvShowWithTvShow>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteTvShow(favoriteTvShow: List<FavoriteTvShow>)

    @Update
    suspend fun favoriteTvShow(favoriteTvShow: FavoriteTvShow)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVShow(tvShowEntities: List<TVShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeasons(seasons: List<Season>)

    @Delete
    suspend fun delete(tvShowEntity: TVShowEntity)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

}