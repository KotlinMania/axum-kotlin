// port-lint: tests routing/method_filter.rs
package io.github.kotlinmania.axum.routing

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MethodFilterTest {
    @Test
    fun fromHttpMethod() {
        assertEquals(MethodFilter.CONNECT, MethodFilter.tryFrom("CONNECT"))
        assertEquals(MethodFilter.DELETE, MethodFilter.tryFrom("DELETE"))
        assertEquals(MethodFilter.GET, MethodFilter.tryFrom("GET"))
        assertEquals(MethodFilter.HEAD, MethodFilter.tryFrom("HEAD"))
        assertEquals(MethodFilter.OPTIONS, MethodFilter.tryFrom("OPTIONS"))
        assertEquals(MethodFilter.PATCH, MethodFilter.tryFrom("PATCH"))
        assertEquals(MethodFilter.POST, MethodFilter.tryFrom("POST"))
        assertEquals(MethodFilter.PUT, MethodFilter.tryFrom("PUT"))
        assertEquals(MethodFilter.TRACE, MethodFilter.tryFrom("TRACE"))

        val error =
            assertFailsWith<NoMatchingMethodFilter> {
                MethodFilter.tryFrom("CUSTOM")
            }
        assertTrue(error.message?.contains("CUSTOM") == true)
    }

    @Test
    fun containsAndOr() {
        val getOrPost = MethodFilter.GET.or(MethodFilter.POST)
        assertTrue(getOrPost.contains(MethodFilter.GET))
        assertTrue(getOrPost.contains(MethodFilter.POST))
        assertFalse(getOrPost.contains(MethodFilter.DELETE))

        val combined = MethodFilter.GET + MethodFilter.DELETE
        assertTrue(combined.contains(MethodFilter.GET))
        assertTrue(combined.contains(MethodFilter.DELETE))
        assertFalse(combined.contains(MethodFilter.POST))
    }
}
