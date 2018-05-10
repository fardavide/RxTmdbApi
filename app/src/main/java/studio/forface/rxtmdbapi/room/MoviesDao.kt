package studio.forface.rxtmdbapi.room

import android.arch.persistence.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import studio.forface.rxtmdbapi.models.Fields
import studio.forface.rxtmdbapi.models.Movie


@Dao
interface MoviesDao {

    @Query("SELECT * from ${Movie.TABLE_NAME} where ${Fields.ID} = :id LIMIT 1")
    fun getMovie(id: Int): Single<Movie>

    @Query("SELECT * from ${Movie.TABLE_NAME} where ${Fields.ID} = :id LIMIT 1")
    fun observeMovie(id: Int): Flowable<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie( movie: Movie )

    @Update
    fun updateMovie( movie: Movie )

    @Delete
    fun deleteMovie( movie: Movie )

}

fun MoviesDao.insertMovieAsync( movie: Movie ): Completable =
        Completable.fromAction { insertMovie(movie) }

fun MoviesDao.updateMovieAsync( movie: Movie ): Completable =
        Completable.fromAction{ updateMovie(movie) }

fun MoviesDao.updateOrInsertAsync(movie: Movie ): Completable =
        getMovie( movie.id )
                .flatMapCompletable { updateMovieAsync(movie) }
                .onErrorResumeNext { insertMovieAsync(movie) }

fun MoviesDao.deleteMovieAsync( movie: Movie ): Completable =
        Completable.fromAction { deleteMovie(movie) }
