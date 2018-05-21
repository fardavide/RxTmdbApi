@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package studio.forface.rxtmdbapi.room

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import studio.forface.rxtmdbapi.models.ImagesConfig

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

@Dao
interface ImagesConfigsDao: SingleEntityDao<ImagesConfig> {

    @Query("SELECT * from ${ImagesConfig.TABLE_NAME} LIMIT 1")
    override fun get(): Single<ImagesConfig>

    @Query("SELECT * from ${ImagesConfig.TABLE_NAME} LIMIT 1")
    override fun observe(): Flowable<ImagesConfig>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert( imagesConfig: ImagesConfig)

    @Update
    override fun update( imagesConfig: ImagesConfig)

    @Delete
    override fun delete( imagesConfig: ImagesConfig)

}