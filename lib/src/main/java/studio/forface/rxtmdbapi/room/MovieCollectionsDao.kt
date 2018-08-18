@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package studio.forface.rxtmdbapi.room

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import studio.forface.rxtmdbapi.models.Fields
import studio.forface.rxtmdbapi.models.MovieCollection

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

@Dao
interface MovieCollectionsDao : IntIdDao<MovieCollection> {

    @Query("SELECT * from ${MovieCollection.TABLE_NAME} where ${Fields.ID} = :id LIMIT 1")
    override fun get( id: Int ): Single<MovieCollection>

    @Query("SELECT * from ${MovieCollection.TABLE_NAME} where ${Fields.ID} = :id LIMIT 1")
    override fun observe( id: Int ): Flowable<MovieCollection>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert( movieCollection: MovieCollection )

    @Update
    override fun update( movieCollection: MovieCollection )

    @Delete
    override fun delete( movieCollection: MovieCollection )

}

