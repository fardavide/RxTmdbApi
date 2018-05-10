package studio.forface.rxtmdbapi.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import studio.forface.rxtmdbapi.models.Movie


@Database(entities = [
    Movie::class
], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract val moviesDao: MoviesDao

}