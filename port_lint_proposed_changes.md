# port-lint Proposed Changes

**Generated:** 2026-08-24
**Source:** tmp/axum
**Target:** src

These are review proposals only. They are emitted when a Rust -> Kotlin pair matches only after fallback normalization, so the existing `port-lint` header is not an exact provenance match.

| Target file | Current header | Proposed header | Source path | Reason |
|-------------|----------------|-----------------|-------------|--------|
| `commonMain/kotlin/io/github/kotlinmania/axum/Extension.kt` | `// port-lint: source extension.rs` | `// port-lint: source extension.rs` | `extension.rs` | `port-lint provenance header matched only after fallback normalization: 'extension.rs' vs expected 'extension.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/axum/response/Sse.kt` | `// port-lint: source response/sse.rs` | `// port-lint: source response/sse.rs` | `response/sse.rs` | `port-lint provenance header matched only after fallback normalization: 'response/sse.rs' vs expected 'response/sse.rs'` |
| `commonTest/kotlin/io/github/kotlinmania/axum/response/SseTest.kt` | `// port-lint: tests response/sse.rs` | `// port-lint: tests response/sse.rs` | `response/sse.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:response/sse.rs' vs expected 'response/sse.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/axum/response/Redirect.kt` | `// port-lint: source response/redirect.rs` | `// port-lint: source response/redirect.rs` | `response/redirect.rs` | `port-lint provenance header matched only after fallback normalization: 'response/redirect.rs' vs expected 'response/redirect.rs'` |
| `commonTest/kotlin/io/github/kotlinmania/axum/response/RedirectTest.kt` | `// port-lint: tests response/redirect.rs` | `// port-lint: tests response/redirect.rs` | `response/redirect.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:response/redirect.rs' vs expected 'response/redirect.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/axum/routing/StripPrefix.kt` | `// port-lint: source routing/strip_prefix.rs` | `// port-lint: source routing/strip_prefix.rs` | `routing/strip_prefix.rs` | `port-lint provenance header matched only after fallback normalization: 'routing/strip_prefix.rs' vs expected 'routing/strip_prefix.rs'` |
| `commonTest/kotlin/io/github/kotlinmania/axum/routing/StripPrefixTest.kt` | `// port-lint: tests routing/strip_prefix.rs` | `// port-lint: tests routing/strip_prefix.rs` | `routing/strip_prefix.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:routing/strip_prefix.rs' vs expected 'routing/strip_prefix.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/axum/Util.kt` | `// port-lint: source util.rs` | `// port-lint: source util.rs` | `util.rs` | `port-lint provenance header matched only after fallback normalization: 'util.rs' vs expected 'util.rs'` |
| `commonTest/kotlin/io/github/kotlinmania/axum/UtilTest.kt` | `// port-lint: tests util.rs` | `// port-lint: tests util.rs` | `util.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:util.rs' vs expected 'util.rs'` |
| `commonMain/kotlin/io/github/kotlinmania/axum/routing/MethodFilter.kt` | `// port-lint: source routing/method_filter.rs` | `// port-lint: source routing/method_filter.rs` | `routing/method_filter.rs` | `port-lint provenance header matched only after fallback normalization: 'routing/method_filter.rs' vs expected 'routing/method_filter.rs'` |
| `commonTest/kotlin/io/github/kotlinmania/axum/routing/MethodFilterTest.kt` | `// port-lint: tests routing/method_filter.rs` | `// port-lint: tests routing/method_filter.rs` | `routing/method_filter.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:routing/method_filter.rs' vs expected 'routing/method_filter.rs'` |
