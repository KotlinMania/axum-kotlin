// port-lint: source axum/src/response/redirect.rs
package io.github.kotlinmania.axum.response

/**
 * Response that redirects the request to another location.
 *
 * @property statusCode The HTTP status code of the redirection.
 * @property location The target redirect location URI.
 */
class Redirect private constructor(
    val statusCode: Int,
    val location: String,
) {
    companion object {
        /** HTTP 303 See Other status code. */
        const val STATUS_SEE_OTHER: Int = 303

        /** HTTP 307 Temporary Redirect status code. */
        const val STATUS_TEMPORARY_REDIRECT: Int = 307

        /** HTTP 308 Permanent Redirect status code. */
        const val STATUS_PERMANENT_REDIRECT: Int = 308

        /**
         * Create a new [Redirect] that uses a `303 See Other` status code.
         */
        fun to(uri: String): Redirect = withStatusCode(STATUS_SEE_OTHER, uri)

        /**
         * Create a new [Redirect] that uses a `307 Temporary Redirect` status code.
         */
        fun temporary(uri: String): Redirect = withStatusCode(STATUS_TEMPORARY_REDIRECT, uri)

        /**
         * Create a new [Redirect] that uses a `308 Permanent Redirect` status code.
         */
        fun permanent(uri: String): Redirect = withStatusCode(STATUS_PERMANENT_REDIRECT, uri)

        private fun withStatusCode(statusCode: Int, uri: String): Redirect {
            require(statusCode in 300..399) { "not a redirection status code: $statusCode" }
            return Redirect(statusCode, uri)
        }
    }

    /**
     * Checks whether the location header contains invalid characters such as newlines.
     */
    fun isValidLocation(): Boolean = !location.contains('\n') && !location.contains('\r')

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Redirect) return false
        return statusCode == other.statusCode && location == other.location
    }

    override fun hashCode(): Int {
        var result = statusCode
        result = 31 * result + location.hashCode()
        return result
    }

    override fun toString(): String = "Redirect(statusCode=$statusCode, location='$location')"
}
