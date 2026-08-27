// port-lint: tests axum/src/json.rs
package io.github.kotlinmania.axum

import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Serializable
data class Input(
    val foo: String,
)

@Serializable
data class FooData(
    val a: Int,
    val b: List<BarData>,
)

@Serializable
data class BarData(
    val x: Int,
    val y: Int,
)

class JsonTest {
    @Test
    fun testDeserializeBody() {
        val jsonStr = """{"foo":"bar"}"""
        val result = Json.fromString(Input.serializer(), jsonStr)
        assertTrue(result.isSuccess)
        assertEquals("bar", result.getOrThrow().value.foo)
    }

    @Test
    fun testJsonContentTypes() {
        assertTrue(Json.isJsonContentType("application/json"))
        assertTrue(Json.isJsonContentType("application/json; charset=utf-8"))
        assertTrue(Json.isJsonContentType("application/json;charset=utf-8"))
        assertTrue(Json.isJsonContentType("application/cloudevents+json"))
        assertFalse(Json.isJsonContentType("text/json"))
        assertFalse(Json.isJsonContentType("text/plain"))
        assertFalse(Json.isJsonContentType(null))
    }

    @Test
    fun testInvalidJsonSyntax() {
        val result = Json.fromString(Input.serializer(), "{")
        assertTrue(result.isFailure)
    }

    @Test
    fun testInvalidJsonData() {
        val result = Json.fromString(FooData.serializer(), """{"a": 1, "b": [{"x": 2}]}""")
        assertTrue(result.isFailure)
    }

    @Test
    fun testSerializeBody() {
        val input = Json(Input(foo = "hello"))
        val jsonStr = input.toString(Input.serializer())
        assertEquals("""{"foo":"hello"}""", jsonStr)
    }
}
