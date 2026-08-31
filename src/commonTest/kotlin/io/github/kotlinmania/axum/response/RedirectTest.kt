// port-lint: tests axum/src/response/redirect.rs
package io.github.kotlinmania.axum.response

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RedirectTest {
    companion object {
        private const val EXAMPLE_URL = "https://example.com"
    }

    @Test
    fun correctStatus() {
        assertEquals(Redirect.STATUS_SEE_OTHER, Redirect.to(EXAMPLE_URL).statusCode)
        assertEquals(Redirect.STATUS_TEMPORARY_REDIRECT, Redirect.temporary(EXAMPLE_URL).statusCode)
        assertEquals(Redirect.STATUS_PERMANENT_REDIRECT, Redirect.permanent(EXAMPLE_URL).statusCode)
    }

    @Test
    fun correctLocation() {
        assertEquals(EXAMPLE_URL, Redirect.permanent(EXAMPLE_URL).location)
        assertEquals("/redirect", Redirect.permanent("/redirect").location)
    }

    @Test
    fun testInternalErrorOnNewlines() {
        val redirect = Redirect.permanent("Axum is awesome, \n but newlines aren't allowed :(")
        assertFalse(redirect.isValidLocation())

        val validRedirect = Redirect.permanent(EXAMPLE_URL)
        assertTrue(validRedirect.isValidLocation())
    }
}
