// port-lint: tests response/sse.rs
package io.github.kotlinmania.axum.response

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds

class SseTest {
    @Test
    fun eventFormatting() {
        val event1 =
            Event()
                .data("one")
                .comment("this is a comment")

        val formatted1 = event1.format()
        assertTrue(formatted1.contains(": this is a comment\n"))
        assertTrue(formatted1.contains("data: one\n"))
        assertTrue(formatted1.endsWith("\n\n"))

        val event2 =
            Event()
                .eventType("three")
                .retry(30.seconds)
                .id("unique-id")

        val formatted2 = event2.format()
        assertTrue(formatted2.contains("event: three\n"))
        assertTrue(formatted2.contains("retry: 30000\n"))
        assertTrue(formatted2.contains("id: unique-id\n"))
    }

    @Test
    fun multilineData() {
        val event = Event().data("line1\nline2\nline3")
        val formatted = event.format()
        assertEquals("data: line1\ndata: line2\ndata: line3\n\n", formatted)
    }

    @Test
    fun validationPanics() {
        assertFailsWith<IllegalArgumentException> {
            Event().eventType("line1\nline2")
        }

        assertFailsWith<IllegalArgumentException> {
            Event().id("id\u0000null")
        }

        assertFailsWith<IllegalArgumentException> {
            Event().comment("comment\nwith newline")
        }
    }
}
