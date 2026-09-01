// port-lint: tests util.rs
package io.github.kotlinmania.axum

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class UtilTest {
    @Test
    fun percentDecodedStr() {
        val decoded = PercentDecodedStr.new("hello%20world")
        assertNotNull(decoded)
        assertEquals("hello world", decoded.value)

        val simple = PercentDecodedStr.new("hello")
        assertNotNull(simple)
        assertEquals("hello", simple.value)
    }

    @Test
    fun eitherType() {
        val left: Either<Int, String> = Either.Left(42)
        val right: Either<Int, String> = Either.Right("foo")

        when (left) {
            is Either.Left -> assertEquals(42, left.value)
            is Either.Right -> error("unexpected right")
        }

        when (right) {
            is Either.Left -> error("unexpected left")
            is Either.Right -> assertEquals("foo", right.value)
        }
    }
}
