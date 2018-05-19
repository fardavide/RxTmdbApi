package studio.forface.rxtmdbapi.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import studio.forface.rxtmdbapi.models.*


@Database(entities = [
    Company::class,
    Movie::class,
    MovieCollection::class,
    Person::class,
    Session::class,
    TvShow::class
], version = 1)
abstract class LocalDatabase : RoomDatabase() {

    abstract val companiesDao:          CompaniesDao
    abstract val moviesDao:             MoviesDao
    abstract val movieCollectionsDao:   MovieCollectionsDao
    abstract val peopleDao:             PeopleDao
    abstract val sessionsDao:           SessionsDao
    abstract val tvShowsDao:            TvShowsDao

}