// port-lint: source routing/method_filter.rs
package io.github.kotlinmania.axum.routing

/**
 * A filter that matches one or more HTTP methods.
 */
data class MethodFilter(
    val bits: Int,
) {
    companion object {
        /** Match `CONNECT` requests. */
        val CONNECT: MethodFilter = MethodFilter(1 shl 0)

        /** Match `DELETE` requests. */
        val DELETE: MethodFilter = MethodFilter(1 shl 1)

        /** Match `GET` requests. */
        val GET: MethodFilter = MethodFilter(1 shl 2)

        /** Match `HEAD` requests. */
        val HEAD: MethodFilter = MethodFilter(1 shl 3)

        /** Match `OPTIONS` requests. */
        val OPTIONS: MethodFilter = MethodFilter(1 shl 4)

        /** Match `PATCH` requests. */
        val PATCH: MethodFilter = MethodFilter(1 shl 5)

        /** Match `POST` requests. */
        val POST: MethodFilter = MethodFilter(1 shl 6)

        /** Match `PUT` requests. */
        val PUT: MethodFilter = MethodFilter(1 shl 7)

        /** Match `TRACE` requests. */
        val TRACE: MethodFilter = MethodFilter(1 shl 8)

        /** Match all standard methods. */
        val ALL: MethodFilter = MethodFilter((1 shl 9) - 1)

        /** Match no methods. */
        val NONE: MethodFilter = MethodFilter(0)

        /**
         * Creates a [MethodFilter] from a method name string.
         *
         * @throws NoMatchingMethodFilter if the method cannot be converted.
         */
        fun tryFrom(method: String): MethodFilter =
            when (method.uppercase()) {
                "CONNECT" -> CONNECT
                "DELETE" -> DELETE
                "GET" -> GET
                "HEAD" -> HEAD
                "OPTIONS" -> OPTIONS
                "PATCH" -> PATCH
                "POST" -> POST
                "PUT" -> PUT
                "TRACE" -> TRACE
                else -> throw NoMatchingMethodFilter(method)
            }
    }

    /**
     * Checks if this filter contains all the methods in [other].
     */
    fun contains(other: MethodFilter): Boolean = (bits and other.bits) == other.bits

    /**
     * Performs the OR operation between this [MethodFilter] and [other].
     */
    fun or(other: MethodFilter): MethodFilter = MethodFilter(bits or other.bits)

    /**
     * Operator overload for combining filters.
     */
    operator fun plus(other: MethodFilter): MethodFilter = or(other)
}

/**
 * Error thrown when converting a method name to a [MethodFilter] fails.
 */
class NoMatchingMethodFilter(
    val method: String,
) : IllegalArgumentException("no `MethodFilter` for `$method`")
