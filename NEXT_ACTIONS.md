# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 6/60 (10.0%)
- **Function parity:** 20/674 matched (target 66) — 3.0%
- **Class/type parity:** 10/212 matched (target 18) — 4.7%
- **Combined symbol parity:** 30/886 matched (target 84) — 3.4%
- **Average inline-code cosine:** 0.18 (function body across 6 matched files)
- **Average documentation cosine:** 0.41 (doc text across 6 matched files)
- **Cheat-zeroed Files:** 1
- **Critical Issues:** 6 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

1. **handler.service** (23 deps)
   - Path: `src/handler/service.rs`
   - Essential for 23 other files

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. extension

- **Target:** `axum.Extension [ZERO] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 3
- **Priority Score:** 3192110.0
- **Functions:** 0/12 matched (target 0)
- **Missing functions:** `from_extensions`, `from_request_parts`, `into_response_parts`, `into_response`, `layer`, `poll_ready`, `call`, `extension_extractor`, `requires_foo`, `optional_foo`, `requires_bar`, `optional_bar`
- **Types:** 2/9 matched (target 2)
- **Missing types:** `Rejection`, `Error`, `Service`, `Response`, `Future`, `Foo`, `Bar`
- **Tests:** 0/5 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `extension.rs` vs expected `extension.rs`
- **Proposed provenance header:** `// port-lint: source extension.rs` (current: `// port-lint: source extension.rs`)
- **Lint issues:** 1

### 2. response.sse

- **Target:** `response.Sse [PROVENANCE-FALLBACK]`
- **Similarity:** 0.10
- **Dependents:** 1
- **Priority Score:** 1364609.0
- **Functions:** 7/36 matched (target 13)
- **Missing functions:** `new`, `fmt`, `into_response`, `poll_frame`, `as_mut`, `finalized`, `into_data_writer`, `json_data`, `write`, `flush`, `event`, `field`, `finalize`, `into_event`, `write_buf`, `write_str`, `default`, `bits`, `from_bits`, `contains`, `insert`, `reset`, `poll_next`, `leading_space_is_not_stripped`, `write_data_writer_str`, `valid_json_raw_value_chars_handled`, `basic`, `keep_alive_ends_when_the_stream_ends`, `parse_event`
- **Types:** 3/10 matched (target 4)
- **Missing types:** `Data`, `Error`, `Buffer`, `EventDataWriter`, `JsonWriter`, `EventFlags`, `Item`
- **Tests:** 0/6 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `response/sse.rs` vs expected `response/sse.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:response/sse.rs` vs expected `response/sse.rs`
- **Proposed provenance header:** `// port-lint: source response/sse.rs` (current: `// port-lint: source response/sse.rs`)
- **Proposed provenance header:** `// port-lint: tests response/sse.rs` (current: `// port-lint: tests response/sse.rs`)
- **Lint issues:** 2

### 3. response.redirect

- **Target:** `response.Redirect [PROVENANCE-FALLBACK]`
- **Similarity:** 0.47
- **Dependents:** 1
- **Priority Score:** 1041105.3
- **Functions:** 6/10 matched (target 11)
- **Missing functions:** `status_code`, `location`, `into_response`, `test_internal_error`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 2/3 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `response/redirect.rs` vs expected `response/redirect.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:response/redirect.rs` vs expected `response/redirect.rs`
- **Proposed provenance header:** `// port-lint: source response/redirect.rs` (current: `// port-lint: source response/redirect.rs`)
- **Proposed provenance header:** `// port-lint: tests response/redirect.rs` (current: `// port-lint: tests response/redirect.rs`)
- **Lint issues:** 2

### 4. routing.strip_prefix

- **Target:** `routing.StripPrefix [PROVENANCE-FALLBACK]`
- **Similarity:** 0.12
- **Dependents:** 0
- **Priority Score:** 151808.8
- **Functions:** 2/11 matched (target 32)
- **Missing functions:** `layer`, `poll_ready`, `call`, `strip_prefix`, `zip_longest`, `does_not_panic`, `arbitrary`, `ascii_alphanumeric`, `u8_between`
- **Types:** 1/7 matched (target 2)
- **Missing types:** `Response`, `Error`, `Future`, `Item`, `UriAndPrefix`, `AsciiAlphanumeric`
- **Tests:** 0/4 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `routing/strip_prefix.rs` vs expected `routing/strip_prefix.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:routing/strip_prefix.rs` vs expected `routing/strip_prefix.rs`
- **Proposed provenance header:** `// port-lint: source routing/strip_prefix.rs` (current: `// port-lint: source routing/strip_prefix.rs`)
- **Proposed provenance header:** `// port-lint: tests routing/strip_prefix.rs` (current: `// port-lint: tests routing/strip_prefix.rs`)
- **Lint issues:** 2

### 5. util

- **Target:** `axum.Util [PROVENANCE-FALLBACK]`
- **Similarity:** 0.03
- **Dependents:** 0
- **Priority Score:** 131509.7
- **Functions:** 1/8 matched (target 4)
- **Missing functions:** `as_str`, `deref`, `poll_ready`, `call`, `poll`, `try_downcast`, `test_try_downcast`
- **Types:** 1/7 matched (target 5)
- **Missing types:** `Target`, `MapIntoResponse`, `Response`, `Error`, `Future`, `Output`
- **Tests:** 0/1 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `util.rs` vs expected `util.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:util.rs` vs expected `util.rs`
- **Proposed provenance header:** `// port-lint: source util.rs` (current: `// port-lint: source util.rs`)
- **Proposed provenance header:** `// port-lint: tests util.rs` (current: `// port-lint: tests util.rs`)
- **Lint issues:** 2

### 6. routing.method_filter

- **Target:** `routing.MethodFilter [PROVENANCE-FALLBACK]`
- **Similarity:** 0.35
- **Dependents:** 0
- **Priority Score:** 51106.5
- **Functions:** 4/8 matched (target 6)
- **Missing functions:** `bits`, `from_bits`, `method`, `fmt`
- **Types:** 2/3 matched
- **Missing types:** `Error`
- **Tests:** 1/1 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `routing/method_filter.rs` vs expected `routing/method_filter.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:routing/method_filter.rs` vs expected `routing/method_filter.rs`
- **Proposed provenance header:** `// port-lint: source routing/method_filter.rs` (current: `// port-lint: source routing/method_filter.rs`)
- **Proposed provenance header:** `// port-lint: tests routing/method_filter.rs` (current: `// port-lint: tests routing/method_filter.rs`)
- **Lint issues:** 2

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

## Reexport / Wiring Modules

These files match `reexport_modules` patterns in `.ast_distance_config.json`. They are filtered out of
normal priority and missing-file ladders because they are wiring
modules, not direct logic ports. Consult them for call-site routing;
do not treat them as the next implementation target by default.

### Missing

| Source | Expected target | Deps | Source path | Expected path |
|--------|-----------------|------|-------------|---------------|
| `body.mod` | `body.Mod` | 0 | `src/body/mod.rs` | `body/Mod.kt` |
| `error_handling.mod` | `errorhandling.Mod` | 0 | `src/error_handling/mod.rs` | `errorhandling/Mod.kt` |
| `extract.mod` | `extract.Mod` | 0 | `src/extract/mod.rs` | `extract/Mod.kt` |
| `path.mod` | `extract.path.Mod` | 0 | `src/extract/path/mod.rs` | `extract/path/Mod.kt` |
| `handler.mod` | `handler.Mod` | 0 | `src/handler/mod.rs` | `handler/Mod.kt` |
| `lib` | `Lib` | 0 | `src/lib.rs` | `Lib.kt` |
| `middleware.mod` | `middleware.Mod` | 0 | `src/middleware/mod.rs` | `middleware/Mod.kt` |
| `response.mod` | `response.Mod` | 0 | `src/response/mod.rs` | `response/Mod.kt` |
| `routing.mod` | `routing.Mod` | 0 | `src/routing/mod.rs` | `routing/Mod.kt` |
| `tests.mod` | `routing.tests.Mod` | 0 | `src/routing/tests/mod.rs` | `routing/tests/Mod.kt` |
| `serve.mod` | `serve.Mod` | 0 | `src/serve/mod.rs` | `serve/Mod.kt` |
| `test_helpers.mod` | `testhelpers.Mod` | 0 | `src/test_helpers/mod.rs` | `testhelpers/Mod.kt` |

