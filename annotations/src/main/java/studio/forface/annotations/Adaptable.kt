package studio.forface.annotations

/**
 * An interface that works as a wrapper for the values returned by an [AdaptableClass].
 * @see AdaptableClass.generatedTypes
 * Eg:
 *      > fun getMovie(): Adaptable<Movie>
 *
 * Will become:
 *      > fun getMovie(): Single<Movie>
 * and
 *      > fun getMovie(): Deferred<Movie>
 */
interface Adaptable<T>