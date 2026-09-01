// port-lint: source util.rs
package io.github.kotlinmania.axum

/**
 * A percent-decoded string wrapper.
 */
data class PercentDecodedStr(
    val value: String,
) {
    companion object {
        /**
         * Decodes a percent-encoded string.
         */
        fun new(s: String): PercentDecodedStr? =
            try {
                PercentDecodedStr(decodePercent(s))
            } catch (_: Exception) {
                null
            }

        private fun decodePercent(s: String): String {
            val bytes = mutableListOf<Byte>()
            var i = 0
            while (i < s.length) {
                if (s[i] == '%' && i + 2 < s.length) {
                    val hex = s.substring(i + 1, i + 3)
                    val byteVal = hex.toIntOrNull(16)
                    if (byteVal != null) {
                        bytes.add(byteVal.toByte())
                        i += 3
                        continue
                    }
                }
                bytes.addAll(s[i].toString().encodeToByteArray().toList())
                i++
            }
            return bytes.toByteArray().decodeToString()
        }
    }
}

/**
 * A sum type representing either value [A] or value [B].
 */
sealed class Either<out A, out B> {
    data class Left<out A>(
        val value: A,
    ) : Either<A, Nothing>()

    data class Right<out B>(
        val value: B,
    ) : Either<Nothing, B>()
}
