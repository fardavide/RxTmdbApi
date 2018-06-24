package studio.forface.rxtmdbapi.room

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import studio.forface.rxtmdbapi.models.*
import studio.forface.rxtmdbapi.tmdb.Session


@Database(entities = [
    Company::class,
    ImagesConfig::class,
    Movie::class,
    MovieCollection::class,
    Person::class,
    Session::class,
    TvShow::class
], version = 1)
abstract class TmdbDatabase : RoomDatabase() {

    abstract val companiesDao:          CompaniesDao
    abstract val imagesConfigsDao:      ImagesConfigsDao
    abstract val moviesDao:             MoviesDao
    abstract val movieCollectionsDao:   MovieCollectionsDao
    abstract val peopleDao:             PeopleDao
    abstract val sessionsDao:           SessionsDao
    abstract val tvShowsDao:            TvShowsDao

    companion object {

        private var database: TmdbDatabase? = null
        private val callers = mutableSetOf<Any>()

        private val observer = object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
            fun onLifecycleChange() {
                callers.mapNotNull { it as? LifecycleOwner }
                        .filter { it.lifecycle.currentState == Lifecycle.State.DESTROYED }
                        .forEach {
                            it.lifecycle.removeObserver(this  )
                            closeHere( it )
                        }
            }
        }

        fun get( lifecycleOwner: LifecycleOwner ): TmdbDatabase {
            lifecycleOwner.lifecycle.addObserver( observer )
            callers.add( lifecycleOwner )
            return openIfNeeded( lifecycleOwner as Context )
        }

        fun get( caller: Any, context: Context, forceNoLifecycle: Boolean = false ): TmdbDatabase {
            if ( caller is LifecycleOwner && ! forceNoLifecycle )
                throw Exception( "the caller param has a Lifecycle, use get( lifecycleOwner:" +
                        " LifecycleOwner ) or set forceNoLifecycle as true" )
            
            callers.add( caller )
            return openIfNeeded( context )
        }

        private fun openIfNeeded( context: Context ): TmdbDatabase {
            if ( database?.isOpen != true ) database = build( context )
            return database!!
        }

        fun closeHere( caller: Any ) {
            callers.remove( caller )
            closeIfNeeded()
        }

        private fun closeIfNeeded() {
            if ( callers.isEmpty() ) {
                database?.close()
                database = null
            }
        }

        private fun build( context: Context ) = Room.databaseBuilder(
                context.applicationContext,
                TmdbDatabase::class.java,
                "Tmdb_database"
        ).build()
    }

}