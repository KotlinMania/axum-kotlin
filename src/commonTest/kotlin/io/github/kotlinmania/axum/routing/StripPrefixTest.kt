// port-lint: tests axum/src/routing/strip_prefix.rs
package io.github.kotlinmania.axum.routing

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class StripPrefixTest {
    @Test
    fun empty() {
        assertEquals("/", StripPrefix.strip("/", "/"))
    }

    @Test
    fun singleSegment() {
        assertEquals("/", StripPrefix.strip("/a", "/a"))
    }

    @Test
    fun singleSegmentRootUri() {
        assertNull(StripPrefix.strip("/", "/a"))
    }

    @Test
    fun singleSegmentRootPrefix() {
        assertEquals("/a", StripPrefix.strip("/a", "/"))
    }

    @Test
    fun singleSegmentNoMatch() {
        assertNull(StripPrefix.strip("/a", "/b"))
    }

    @Test
    fun singleSegmentTrailingSlash() {
        assertEquals("/", StripPrefix.strip("/a/", "/a/"))
    }

    @Test
    fun singleSegmentTrailingSlash2() {
        assertNull(StripPrefix.strip("/a", "/a/"))
    }

    @Test
    fun singleSegmentTrailingSlash3() {
        assertEquals("/", StripPrefix.strip("/a/", "/a"))
    }

    @Test
    fun multiSegment() {
        assertEquals("/b", StripPrefix.strip("/a/b", "/a"))
    }

    @Test
    fun multiSegment2() {
        assertNull(StripPrefix.strip("/b/a", "/a"))
    }

    @Test
    fun multiSegment3() {
        assertNull(StripPrefix.strip("/a", "/a/b"))
    }

    @Test
    fun multiSegment4() {
        assertNull(StripPrefix.strip("/a/b", "/b"))
    }

    @Test
    fun multiSegmentTrailingSlash() {
        assertEquals("/", StripPrefix.strip("/a/b/", "/a/b/"))
    }

    @Test
    fun multiSegmentTrailingSlash2() {
        assertNull(StripPrefix.strip("/a/b", "/a/b/"))
    }

    @Test
    fun multiSegmentTrailingSlash3() {
        assertEquals("/", StripPrefix.strip("/a/b/", "/a/b"))
    }

    @Test
    fun param0() {
        assertEquals("/", StripPrefix.strip("/", "/{param}"))
    }

    @Test
    fun param1() {
        assertEquals("/", StripPrefix.strip("/a", "/{param}"))
    }

    @Test
    fun param2() {
        assertEquals("/b", StripPrefix.strip("/a/b", "/{param}"))
    }

    @Test
    fun param3() {
        assertEquals("/a", StripPrefix.strip("/b/a", "/{param}"))
    }

    @Test
    fun param4() {
        assertEquals("/", StripPrefix.strip("/a/b", "/a/{param}"))
    }

    @Test
    fun param5() {
        assertNull(StripPrefix.strip("/b/a", "/a/{param}"))
    }

    @Test
    fun param6() {
        assertNull(StripPrefix.strip("/a/b", "/{param}/a"))
    }

    @Test
    fun param7() {
        assertEquals("/", StripPrefix.strip("/b/a", "/{param}/a"))
    }

    @Test
    fun param8() {
        assertEquals("/", StripPrefix.strip("/a/b/c", "/a/{param}/c"))
    }

    @Test
    fun param9() {
        assertNull(StripPrefix.strip("/c/b/a", "/a/{param}/c"))
    }

    @Test
    fun param10() {
        assertEquals("/", StripPrefix.strip("/a/", "/{param}"))
    }

    @Test
    fun param11() {
        assertNull(StripPrefix.strip("/a", "/{param}/"))
    }

    @Test
    fun param12() {
        assertEquals("/", StripPrefix.strip("/a/", "/{param}/"))
    }

    @Test
    fun param13() {
        assertEquals("/a", StripPrefix.strip("/a/a", "/a/"))
    }
}
