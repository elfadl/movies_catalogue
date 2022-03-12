package id.elfastudio.moviescatalogue.core.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.*
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteMovie
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteMovieWithMovie
import id.elfastudio.moviescatalogue.core.data.source.local.entity.MovieEntity
import id.elfastudio.moviescatalogue.core.data.source.local.entity.MovieEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow
import id.elfastudio.moviescatalogue.core.data.source.local.entity.FavoriteMovie.Companion.TABLE_NAME as FAVORITE_TABLE

@Dao
interface MovieDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY popularity DESC")
    fun getMovies(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM $FAVORITE_TABLE WHERE movie_id = :movieId")
    fun getFavoriteMovie(movieId: Int): Flow<FavoriteMovie>

    @Transaction
    @Query("SELECT * FROM $FAVORITE_TABLE " +
            "LEFT JOIN $TABLE_NAME ON $FAVORITE_TABLE.movie_id = $TABLE_NAME.movie_id " +
            "WHERE favorite = 1 ORDER BY popularity DESC")
    fun getFavoriteMovies(): Flow<List<FavoriteMovieWithMovie>>

    @Query("SELECT * FROM $TABLE_NAME WHERE movie_id = :movieId LIMIT 1")
    fun getDetailMovie(movieId: Int): Flow<FavoriteMovieWithMovie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteMovie(favoriteMovie: List<FavoriteMovie>)

    @Update
    suspend fun favoriteMovie(favoriteMovie: FavoriteMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntities: List<MovieEntity>)

    @Delete
    suspend fun delete(movieEntity: MovieEntity)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()

}