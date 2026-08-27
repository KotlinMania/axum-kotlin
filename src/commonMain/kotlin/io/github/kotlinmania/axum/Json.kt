// port-lint: source json.rs
package io.github.kotlinmania.axum

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json as KotlinxJson

/**
 * JSON Extractor and Response wrapper.
 *
 * Wraps a payload [value] of type [T] for JSON serialization and deserialization.
 *
 * @param T The type of the wrapped value.
 * @property value The underlying payload value.
 */
data class Json<T>(
    val value: T,
) {
    companion object {
        private val defaultJson: KotlinxJson =
            KotlinxJson {
                ignoreUnknownKeys = true
                isLenient = false
            }

        /**
         * Deserializes a JSON string into a [Json] container using the provided serializer.
         */
        fun <T> fromString(
            serializer: KSerializer<T>,
            jsonString: String,
            json: KotlinxJson = defaultJson,
        ): Result<Json<T>> =
            runCatching {
                Json(json.decodeFromString(serializer, jsonString))
            }

        /**
         * Checks whether the provided HTTP Content-Type header corresponds to JSON.
         */
        fun isJsonContentType(contentType: String?): Boolean {
            if (contentType == null) return false
            val normalized =
                contentType
                    .split(";")
                    .first()
                    .trim()
                    .lowercase()
            return normalized == "application/json" || normalized.endsWith("+json")
        }
    }

    /**
     * Serializes the wrapped value into a JSON string using the provided serializer.
     */
    fun toString(
        serializer: KSerializer<T>,
        json: KotlinxJson = defaultJson,
    ): String = json.encodeToString(serializer, value)
}
