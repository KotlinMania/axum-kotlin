// port-lint: tests extension.rs
package io.github.kotlinmania.axum

import kotlin.test.Test
import kotlin.test.assertEquals

class ExtensionTest {
    @Test
    fun testExtensionCreation() {
        val ext = Extension("state_data")
        assertEquals("state_data", ext.value)
    }

    @Test
    fun testAddExtensionMiddleware() {
        val middleware = AddExtension("inner_service", 42)
        assertEquals("inner_service", middleware.inner)
        assertEquals(42, middleware.value)
    }
}
