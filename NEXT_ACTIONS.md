# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 7/58 (12.1%)
- **Function parity:** 20/665 matched (target 76) — 3.0%
- **Class/type parity:** 12/209 matched (target 24) — 5.7%
- **Combined symbol parity:** 32/874 matched (target 100) — 3.7%
- **Average inline-code cosine:** 0.15 (function body across 7 matched files)
- **Average documentation cosine:** 0.41 (doc text across 7 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 7 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

1. **handler.service** (23 deps)
   - Path: `handler/service.rs`
   - Essential for 23 other files

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. json

- **Target:** `axum.Json`
- **Similarity:** 0.00
- **Dependents:** 4
- **Priority Score:** 4171910.0
- **Functions:** 0/14 matched (target 8)
- **Missing functions:** `from_request`, `json_content_type`, `from`, `from_bytes`, `make_rejection`, `into_response`, `make_response`, `deserialize_body`, `consume_body_to_json_requires_json_content_type`, `json_content_types`, `valid_json_content_type`, `invalid_json_syntax`, `extra_chars_after_valid_json_syntax`, `invalid_json_data`
- **Types:** 2/5 matched
- **Missing types:** `Rejection`, `Foo`, `Bar`
- **Tests:** 0/7 matched

### 2. extension

- **Target:** `axum.Extension`
- **Similarity:** 0.00
- **Dependents:** 3
- **Priority Score:** 3192110.0
- **Functions:** 0/12 matched (target 2)
- **Missing functions:** `from_extensions`, `from_request_parts`, `into_response_parts`, `into_response`, `layer`, `poll_ready`, `call`, `extension_extractor`, `requires_foo`, `optional_foo`, `requires_bar`, `optional_bar`
- **Types:** 2/9 matched (target 3)
- **Missing types:** `Rejection`, `Error`, `Service`, `Response`, `Future`, `Foo`, `Bar`
- **Tests:** 0/5 matched

### 3. response.sse

- **Target:** `response.Sse`
- **Similarity:** 0.10
- **Dependents:** 1
- **Priority Score:** 1364609.0
- **Functions:** 7/36 matched (target 13)
- **Missing functions:** `new`, `fmt`, `into_response`, `poll_frame`, `as_mut`, `finalized`, `into_data_writer`, `json_data`, `write`, `flush`, `event`, `field`, `finalize`, `into_event`, `write_buf`, `write_str`, `default`, `bits`, `from_bits`, `contains`, `insert`, `reset`, `poll_next`, `leading_space_is_not_stripped`, `write_data_writer_str`, `valid_json_raw_value_chars_handled`, `basic`, `keep_alive_ends_when_the_stream_ends`, `parse_event`
- **Types:** 3/10 matched (target 4)
- **Missing types:** `Data`, `Error`, `Buffer`, `EventDataWriter`, `JsonWriter`, `EventFlags`, `Item`
- **Tests:** 0/6 matched

### 4. response.redirect

- **Target:** `response.Redirect`
- **Similarity:** 0.47
- **Dependents:** 1
- **Priority Score:** 1041105.3
- **Functions:** 6/10 matched (target 11)
- **Missing functions:** `status_code`, `location`, `into_response`, `test_internal_error`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 2/3 matched

### 5. routing.strip_prefix

- **Target:** `routing.StripPrefix`
- **Similarity:** 0.12
- **Dependents:** 0
- **Priority Score:** 151808.8
- **Functions:** 2/11 matched (target 32)
- **Missing functions:** `layer`, `poll_ready`, `call`, `strip_prefix`, `zip_longest`, `does_not_panic`, `arbitrary`, `ascii_alphanumeric`, `u8_between`
- **Types:** 1/7 matched (target 2)
- **Missing types:** `Response`, `Error`, `Future`, `Item`, `UriAndPrefix`, `AsciiAlphanumeric`
- **Tests:** 0/4 matched

### 6. util

- **Target:** `axum.Util`
- **Similarity:** 0.03
- **Dependents:** 0
- **Priority Score:** 131509.7
- **Functions:** 1/8 matched (target 4)
- **Missing functions:** `as_str`, `deref`, `poll_ready`, `call`, `poll`, `try_downcast`, `test_try_downcast`
- **Types:** 1/7 matched (target 5)
- **Missing types:** `Target`, `MapIntoResponse`, `Response`, `Error`, `Future`, `Output`
- **Tests:** 0/1 matched

### 7. routing.method_filter

- **Target:** `routing.MethodFilter`
- **Similarity:** 0.35
- **Dependents:** 0
- **Priority Score:** 51106.5
- **Functions:** 4/8 matched (target 6)
- **Missing functions:** `bits`, `from_bits`, `method`, `fmt`
- **Types:** 2/3 matched
- **Missing types:** `Error`
- **Tests:** 1/1 matched

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
| `body.mod` | `body.Mod` | 0 | `body/mod.rs` | `body/Mod.kt` |
| `error_handling.mod` | `errorhandling.Mod` | 0 | `error_handling/mod.rs` | `errorhandling/Mod.kt` |
| `extract.mod` | `extract.Mod` | 0 | `extract/mod.rs` | `extract/Mod.kt` |
| `path.mod` | `extract.path.Mod` | 0 | `extract/path/mod.rs` | `extract/path/Mod.kt` |
| `handler.mod` | `handler.Mod` | 0 | `handler/mod.rs` | `handler/Mod.kt` |
| `lib` | `Lib` | 0 | `lib.rs` | `Lib.kt` |
| `middleware.mod` | `middleware.Mod` | 0 | `middleware/mod.rs` | `middleware/Mod.kt` |
| `response.mod` | `response.Mod` | 0 | `response/mod.rs` | `response/Mod.kt` |
| `routing.mod` | `routing.Mod` | 0 | `routing/mod.rs` | `routing/Mod.kt` |
| `tests.mod` | `routing.tests.Mod` | 0 | `routing/tests/mod.rs` | `routing/tests/Mod.kt` |
| `serve.mod` | `serve.Mod` | 0 | `serve/mod.rs` | `serve/Mod.kt` |
| `test_helpers.mod` | `testhelpers.Mod` | 0 | `test_helpers/mod.rs` | `testhelpers/Mod.kt` |

