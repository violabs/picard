
# Test Failure Analysis Specification

## Objective
Run the gradle test suite, identify all failing tests, and create a summary report as a Kotlin class.

## Steps

### 1. Run Tests
- Execute `./gradlew test` from the project root
- Capture the test output and identify failures

### 2. Parse Test Failures
- Identify which test classes have failures
- Extract the specific test methods that failed
- Capture the failure details:
  - For tests expecting `IllegalArgumentException` that didn't throw: record as "no exception thrown"
  - For other failures: capture EXPECTED vs ACTUAL values

### 3. Create Summary Class
- Location: `/Users/violabs/Projects/picard/core/src/test/TestFailureSummary.kt`
- Format: Kotlin class containing:
  - List of all failed test classes
  - For each class, list the failed test methods
  - For each method, include the failure reason

### 4. Summary Class Structure
```kotlin
package io.violabs.picard

/**
 * Summary of test failures after requireNotEmptyScenario migration
 * Generated: [date]
 */
object TestFailureSummary {
    val failures = mapOf(
        "TestClassName" to listOf(
            TestFailure(
                method = "testMethodName",
                reason = "no exception thrown" // or "EXPECTED: X, ACTUAL: Y"
            )
        )
    )
    
    data class TestFailure(
        val method: String,
        val reason: String
    )
}
```

## Confirmation
After creating the summary file, confirm:
- Total number of failed test classes
- Total number of failed test methods
- File has been created at the specified location