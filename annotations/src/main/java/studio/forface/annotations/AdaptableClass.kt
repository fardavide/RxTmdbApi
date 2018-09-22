@file:Suppress("KDocUnresolvedReference")

package studio.forface.annotations

import kotlin.reflect.KClass

@Retention( AnnotationRetention.SOURCE )
@Target( AnnotationTarget.CLASS )
/** TODO: set proper type for [generatedTypes].
 * An [Annotation] for mark classes or interfaces that are adaptable.
 * Mark a class or interface with this [Annotation] for let the compiler create different
 * classes / interfaces, replacing [Adaptable] with [generatedTypes].
 *
 * @param generatedClassesPrefix is and [Array] of [String] representing the prefixes that will be
 * applied to the generated classes / interfaces. Eg:
 *      > @AdaptableClass( ["Rx", "K"] )
 *      interface TmdbMovies
 *
 * Will generate RxTmdbMovies and KTmdbMovies.
 * @param generatedTypes is an [Array] of [KClass] that will replace [Adaptable] types. Eg:
 *      > @AdaptableClass( ["Rx", "K"], [Single::class, Deferred::class] )
 *      interface TmdbMovies {
 *          fun getMovie(): Adaptable<Movie>
 *      }
 *
 * Will generate the following interfaces:
 *      > interface RxTmdbMovies {
 *          fun getMovie(): Single<Movie>
 *      }
 *
 * and
 *      > interface KTmdbMovies {
 *          fun getMovie(): Deferred<Movie>
 *      }
 *
 * NOTE that [generatedClassesPrefix] and [generatedTypes] must have the same [Array.size],
 * otherwise the smaller[Array] will be used since every class / interface will be generated using
 * `generatedClassesPrefix[N]` and `generatedTypes[N]`
 */
annotation class AdaptableClass(
        val generatedClassesPrefix: Array<String>,
        val generatedTypes: Array<String>
)