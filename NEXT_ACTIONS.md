# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 0/58 (0.0%)
- **Function parity:** 0/674 matched — 0.0%
- **Class/type parity:** 0/209 matched — 0.0%
- **Combined symbol parity:** 0/883 matched — 0.0%
- **Average inline-code cosine:** 0.00 (function body across 0 matched files)
- **Average documentation cosine:** 0.00 (doc text across 0 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 0 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

1. **handler.service** (23 deps)
   - Path: `handler/service.rs`
   - Essential for 23 other files

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

## Next Commands

```bash
# Initialize task queue for systematic porting
cd tools/ast_distance
./ast_distance --init-tasks ../../tmp/axum/src rust ../../src/commonMain/kotlin/io/github/kotlinmania/axum kotlin tasks.json ../../AGENTS.md

# Get next high-priority task
./ast_distance --assign tasks.json <agent-id>
```
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

