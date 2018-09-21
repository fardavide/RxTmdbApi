package studio.forface.annotations

import kotlin.reflect.KClass

@Retention( AnnotationRetention.SOURCE )
@Target( AnnotationTarget.CLASS )
/** TODO: set proper type for [classes].
 * An [Annotation] for mark classes or interfaces that are adaptable.
 * Mark a class or interface with this [Annotation] for let the compiler create different
 * classes / interfaces, replacing [Adaptable] with [classes]. Eg.
 *      > Single<Movie>, Deferred<Movie>
 */
annotation class AdaptableClass( val classes: Array<KClass<*>> )