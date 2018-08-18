@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package studio.forface.rxtmdbapi.room

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import studio.forface.rxtmdbapi.models.Company
import studio.forface.rxtmdbapi.models.Fields

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

@Dao
interface CompaniesDao : IntIdDao<Company> {

    @Query("SELECT * from ${Company.TABLE_NAME} where ${Fields.ID} = :id LIMIT 1")
    override fun get( id: Int ): Single<Company>

    @Query("SELECT * from ${Company.TABLE_NAME} where ${Fields.ID} = :id LIMIT 1")
    override fun observe( id: Int ): Flowable<Company>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert( company: Company )

    @Update
    override fun update( company: Company )

    @Delete
    override fun delete( company: Company )

}

