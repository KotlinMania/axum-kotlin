// port-lint: source axum/src/response/sse.rs
package io.github.kotlinmania.axum.response

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Server-Sent Event representation.
 */
class Event(
    private val data: String? = null,
    private val eventType: String? = null,
    private val id: String? = null,
    private val retryMs: Long? = null,
    private val comments: List<String> = emptyList(),
) {
    companion object {
        /** Default keep-alive event formatted as `:\n\n` */
        val DEFAULT_KEEP_ALIVE: Event = Event(comments = listOf(""))
    }

    /**
     * Sets the event's data field (`data: <content>`).
     */
    fun data(data: String): Event =
        Event(
            data = data,
            eventType = this.eventType,
            id = this.id,
            retryMs = this.retryMs,
            comments = this.comments,
        )

    /**
     * Sets the event's comment field (`:<comment-text>`).
     */
    fun comment(comment: String): Event {
        require(!comment.contains('\n') && !comment.contains('\r')) {
            "SSE comment cannot contain newlines or carriage returns"
        }
        return Event(
            data = this.data,
            eventType = this.eventType,
            id = this.id,
            retryMs = this.retryMs,
            comments = this.comments + comment,
        )
    }

    /**
     * Sets the event's name field (`event: <event-name>`).
     */
    fun eventType(eventType: String): Event {
        require(!eventType.contains('\n') && !eventType.contains('\r')) {
            "SSE event name cannot contain newlines or carriage returns"
        }
        require(this.eventType == null) { "Called `Event.eventType` multiple times" }
        return Event(
            data = this.data,
            eventType = eventType,
            id = this.id,
            retryMs = this.retryMs,
            comments = this.comments,
        )
    }

    /**
     * Sets the event's name field (`event: <event-name>`).
     */
    fun eventName(name: String): Event = eventType(name)

    /**
     * Sets the event's retry timeout field (`retry: <timeout>`).
     */
    fun retry(duration: Duration): Event {
        require(this.retryMs == null) { "Called `Event.retry` multiple times" }
        return Event(
            data = this.data,
            eventType = this.eventType,
            id = this.id,
            retryMs = duration.inWholeMilliseconds,
            comments = this.comments,
        )
    }

    /**
     * Sets the event's identifier field (`id: <identifier>`).
     */
    fun id(id: String): Event {
        require(!id.contains('\n') && !id.contains('\r') && !id.contains('\u0000')) {
            "Event ID cannot contain null characters or newlines"
        }
        require(this.id == null) { "Called `Event.id` multiple times" }
        return Event(
            data = this.data,
            eventType = this.eventType,
            id = id,
            retryMs = this.retryMs,
            comments = this.comments,
        )
    }

    /**
     * Formats the event into its wire protocol representation ending in `\n\n`.
     */
    fun format(): String {
        val sb = StringBuilder()

        for (c in comments) {
            sb
                .append(':')
                .append(' ')
                .append(c)
                .append('\n')
        }

        if (eventType != null) {
            sb.append("event: ").append(eventType).append('\n')
        }

        if (id != null) {
            sb.append("id: ").append(id).append('\n')
        }

        if (retryMs != null) {
            sb.append("retry: ").append(retryMs).append('\n')
        }

        if (data != null) {
            val lines = data.split('\n')
            for (line in lines) {
                val cleanLine = if (line.endsWith('\r')) line.dropLast(1) else line
                sb.append("data: ").append(cleanLine).append('\n')
            }
        }

        sb.append('\n')
        return sb.toString()
    }
}

/**
 * Configuration for Server-Sent Events keep-alive heartbeat.
 */
class KeepAlive(
    val interval: Duration = 15.seconds,
    val text: String = "",
) {
    /** Sets the keep-alive interval. */
    fun interval(duration: Duration): KeepAlive = KeepAlive(duration, text)

    /** Sets the keep-alive comment text. */
    fun text(text: String): KeepAlive = KeepAlive(interval, text)
}

/**
 * An SSE response stream wrapper.
 */
class Sse(
    val flow: Flow<Event>,
    val keepAlive: KeepAlive? = null,
) {
    /** Configures keep-alive messages. */
    fun keepAlive(keepAlive: KeepAlive): Sse = Sse(flow, keepAlive)
}
