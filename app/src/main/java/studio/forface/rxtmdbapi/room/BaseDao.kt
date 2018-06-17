@file:Suppress("unused", "RedundantUnitReturnType")

package studio.forface.rxtmdbapi.room

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import studio.forface.rxtmdbapi.models.IdElement
import studio.forface.rxtmdbapi.models.StringIdElement

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

interface BaseDao<T> {
    fun insert( model: T ) :    Unit
    fun update( model: T ) :    Unit
    fun delete( model: T ) :    Unit
}

interface SingleEntityDao<T> : BaseDao<T> {
    fun get() :                 Single<T>
    fun observe() :             Flowable<T>
}

interface IntIdDao<T: IdElement> : BaseDao<T> {
    fun get( id: Int ) :        Single<T>
    fun observe( id: Int ) :    Flowable<T>
}

interface StringIdDao<T: StringIdElement> : BaseDao<T> {
    fun get( id: String ) :     Single<T>
    fun observe( id: String ) : Flowable<T>
}

// FIXME
interface MultiEntityDao<T, ID> : BaseDao<T> {
    fun get( id: ID ) :         Single<T>
    fun observe( id: ID ) :     Flowable<T>
}


// INSERT.
fun <T> BaseDao<T>.insertAsync(model: T): Completable =
        Completable.fromAction { insert( model ) }

// UPDATE.
fun <T> BaseDao<T>.updateAsync(model: T): Completable =
        Completable.fromAction{ update( model ) }

// UPDATE OR INSERT.
fun <T> SingleEntityDao<T>.updateOrInsertAsync(model: T): Completable =
        get().flatMapCompletable { updateAsync( model ) }
                .onErrorResumeNext { insertAsync( model ) }

fun <T: IdElement> IntIdDao<T>.updateOrInsertAsync(model: T ): Completable =
        get( model.id )
                .flatMapCompletable { updateAsync( model ) }
                .onErrorResumeNext { insertAsync( model ) }

fun <T: StringIdElement> StringIdDao<T>.updateOrInsertAsync(model: T ): Completable =
        get( model.id )
                .flatMapCompletable { updateAsync( model ) }
                .onErrorResumeNext { insertAsync( model ) }

// DELETE.
fun <T> BaseDao<T>.deleteAsync(model: T ): Completable =
        Completable.fromAction { delete( model ) }

fun <T> SingleEntityDao<T>.deleteAsync(): Completable =
        get().flatMapCompletable { deleteAsync( it ) }

fun <T: IdElement> IntIdDao<T>.deleteAsync(id: Int) : Completable =
        get( id ).flatMapCompletable { deleteAsync( it ) }

fun <T: StringIdElement> StringIdDao<T>.deleteAsync(id: String): Completable =
        get( id ).flatMapCompletable { deleteAsync( it ) }