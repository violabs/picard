
# Test File Migration Specification

## Objective
Replace all occurrences of `requireNotEmptyScenario` with `requireScenario` in test files, while keeping the original line commented out for reference.

## Critical Requirement
**YOU MUST HAVE BOTH LINES AND NOT ONLY COMMENT IT OUT!!!!**

## Example Pattern
```kotlin
            possibilities<CertificateSigningRequestList, CertificateSigningRequestListDslBuilder> {
//                requireNotEmptyScenario("items") {
                requireScenario("items") {
                    given(CertificateSigningRequestListDslBuilder())
                }
            }
    }
}
```

## Implementation Plan

### Step 1: Find all files with `requireNotEmptyScenario`
Search for all test files in `/Users/violabs/Projects/picard/core/src/test` that contain `requireNotEmptyScenario`.

### Step 2: For each file, replace the pattern
Transform each occurrence from:
```kotlin
                requireNotEmptyScenario("parameterName") {
```

To:
```kotlin
//                requireNotEmptyScenario("parameterName") {
                requireScenario("parameterName") {
```

### Step 3: Verification
- Ensure the commented line is preserved
- Ensure the new `requireScenario` line is added
- Maintain the same parameter value
- Preserve indentation