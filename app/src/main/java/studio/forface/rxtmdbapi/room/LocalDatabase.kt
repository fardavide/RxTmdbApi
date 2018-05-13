package studio.forface.rxtmdbapi.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import studio.forface.rxtmdbapi.models.Movie
import studio.forface.rxtmdbapi.models.Session
import studio.forface.rxtmdbapi.models.TvShow


@Database(entities = [
    Movie::class,
    Session::class,
    TvShow::class
], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract val moviesDao:     MoviesDao
    abstract val sessionsDao:   SessionsDao
    abstract val tvShowsDao:    TvShowsDao

}