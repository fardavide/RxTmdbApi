@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package studio.forface.rxtmdbapi.room

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import studio.forface.rxtmdbapi.models.Fields
import studio.forface.rxtmdbapi.models.TvShow

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

@Dao
interface TvShowsDao : IntIdDao<TvShow> {

    @Query("SELECT * from ${TvShow.TABLE_NAME} where ${Fields.ID} = :id LIMIT 1")
    override fun get( id: Int ): Single<TvShow>

    @Query("SELECT * from ${TvShow.TABLE_NAME} where ${Fields.ID} = :id LIMIT 1")
    override fun observe( id: Int ): Flowable<TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert( tvShow: TvShow )

    @Update
    override fun update( tvShow: TvShow )

    @Delete
    override fun delete( tvShow: TvShow )

}

