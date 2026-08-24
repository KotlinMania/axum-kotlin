// port-lint: source routing/strip_prefix.rs
package io.github.kotlinmania.axum.routing

/**
 * Utility for stripping prefix paths from URIs.
 */
object StripPrefix {
    /**
     * Strips [prefix] from [uri].
     *
     * Returns the modified URI string if [prefix] matched, or `null` if it did not.
     */
    fun strip(uri: String, prefix: String): String? {
        val queryIndex = uri.indexOf('?')
        val path = if (queryIndex >= 0) uri.substring(0, queryIndex) else uri
        val query = if (queryIndex >= 0) uri.substring(queryIndex + 1) else null

        val pathSegments = segments(path).toList()
        val prefixSegments = segments(prefix).toList()

        var matchingPrefixLength = 0

        val maxLen = maxOf(pathSegments.size, prefixSegments.size)
        for (i in 0 until maxLen) {
            val pathSegment = pathSegments.getOrNull(i)
            val prefixSegment = prefixSegments.getOrNull(i)

            matchingPrefixLength += 1 // count the '/'

            if (pathSegment != null && prefixSegment != null) {
                if (isCapture(prefixSegment) || pathSegment == prefixSegment) {
                    matchingPrefixLength += pathSegment.length
                } else if (prefixSegment.isEmpty()) {
                    // the prefix ended in a '/' so we got a match
                    break
                } else {
                    return null
                }
            } else if (pathSegment != null && prefixSegment == null) {
                // path had more segments than the prefix but we got a match
                break
            } else {
                // the prefix had more segments than the path so there is no match
                return null
            }
        }

        val afterPrefix =
            if (matchingPrefixLength <= path.length) {
                path.substring(matchingPrefixLength)
            } else {
                ""
            }

        val newPath =
            if (afterPrefix.startsWith('/')) {
                afterPrefix
            } else {
                "/$afterPrefix"
            }

        return if (query != null) "$newPath?$query" else newPath
    }

    private fun segments(s: String): Sequence<String> {
        require(s.startsWith('/')) { "path didn't start with '/'. axum should have caught this higher up." }
        return s.splitToSequence('/').drop(1)
    }

    private fun isCapture(segment: String): Boolean =
        segment.startsWith('{') &&
            segment.endsWith('}') &&
            !segment.startsWith("{{") &&
            !segment.endsWith("}}") &&
            !segment.startsWith("{*")
}
