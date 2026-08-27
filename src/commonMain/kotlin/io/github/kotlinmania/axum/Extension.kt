// port-lint: source axum/src/extension.rs
package io.github.kotlinmania.axum

/**
 * Extractor and response wrapper for request extensions.
 *
 * Commonly used to share state across handlers or pass data between middleware and handlers.
 *
 * @param T The type of value stored in the extension.
 * @property value The underlying extension value.
 */
data class Extension<T>(
    val value: T,
)

/**
 * Middleware for adding some shareable value to request extensions.
 */
data class AddExtension<S, T>(
    val inner: S,
    val value: T,
)
