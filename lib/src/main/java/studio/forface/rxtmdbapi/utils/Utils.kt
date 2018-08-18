@file:Suppress("unused")

package studio.forface.rxtmdbapi.utils

/**
 * @author 4face Studio (Davide Giuseppe Farella).
 */

fun <T> redundantTry(
        try1: () -> T,
        try2: () -> T,
        try3: () -> T,
        catch: (Throwable) -> T ): T {

    return try {
        try1()
    } catch ( t: Throwable ) {
        redundantTry( try2, try3, catch )
    }

}

fun <T> redundantTry(
        try1: () -> T,
        try2: () -> T,
        catch: (Throwable) -> T ): T {

    return try {
        try1()
    } catch ( t: Throwable ) {
        redundantTry( try2, catch )
    }

}

fun <T> redundantTry(
        try1: () -> T,
        catch: (Throwable) -> T ): T {

    return try {
        try1()
    } catch ( t: Throwable ) {
        catch(t)
    }

}