@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package studio.forface.rxtmdbapi.room

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import studio.forface.rxtmdbapi.models.Fields
import studio.forface.rxtmdbapi.models.Movie
import studio.forface.rxtmdbapi.models.Person

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

@Dao
interface PeopleDao : IntIdDao<Person> {

    @Query("SELECT * from ${Person.TABLE_NAME} where ${Fields.ID} = :id LIMIT 1")
    override fun get( id: Int ): Single<Person>

    @Query("SELECT * from ${Person.TABLE_NAME} where ${Fields.ID} = :id LIMIT 1")
    override fun observe( id: Int ): Flowable<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert( person: Person )

    @Update
    override fun update( person: Person )

    @Delete
    override fun delete( person: Person )

}

