# Test Plan for Core Module - Missing Coverage

Based on kover report analysis for the core module, the following areas need test coverage. Overall coverage is **89.5%** for classes and **85.6%** for lines, with several packages having 0% or very low coverage.

## Test Framework Summary

The existing test framework uses:
- **JUnit 5** with custom simulation framework
- **Test Types**: `SuccessBuildSim`, `FailureBuildSim`, `FullBuildSim`
- **Setup Pattern**: `buildSetup(TestClass::class, successScenarios, failureScenarios)`
- **Scenarios**: Success scenarios test happy path, failure scenarios test validation failures

## Priority Areas for FailureBuildSim Tests

### 1. Core Package (0% Coverage) - `io.violabs.picard`

**Classes:**
- `CoreDslKt` (core/src/main/kotlin/io/violabs/picard/CoreDsl.kt)
- `YamlBuilder` (core/src/main/kotlin/io/violabs/picard/YamlBuilder.kt)

**Strategy:** 
Create `FailureBuildSim` tests focusing on:
- Invalid DSL configurations
- Missing required parameters in CoreDsl functions
- Invalid YAML generation scenarios in YamlBuilder

### 2. Common Package (0% Coverage) - `io.violabs.picard.common`

**Classes:**
- `AppConstants` (core/src/main/kotlin/io/violabs/picard/common/AppConstants.kt)
- `BuilderGroup` (core/src/main/kotlin/io/violabs/picard/common/BuilderGroup.kt) 
- `UtilsKt` (core/src/main/kotlin/io/violabs/picard/common/Utils.kt)

**Strategy:**
- **AppConstants**: Basic instantiation tests (likely constants, may only need minimal tests)
- **BuilderGroup**: Test validation failures for builder groups
- **UtilsKt**: Test utility function edge cases and invalid inputs

### 3. Serialization Package (14.3% Coverage) - `io.violabs.picard.serialization`

**Classes needing FailureBuildSim:**
- `DataClassSerializer` (0% coverage)
- `IntOrStringSerializer` (0% coverage) 
- `ListAsMapSerializer` (0% coverage)
- `ListAsRetainedQuotesValueMapSerializer`
- `RetainQuotesSerializer`
- `RetainableStringSerializer`

**Strategy:**
Focus on serialization failure cases:
- Invalid data types passed to serializers
- Malformed JSON/serialization inputs
- Null handling edge cases
- Invalid field mappings

### 4. Domain Manifest Package (2.6% Coverage) - `io.violabs.picard.domain.manifest`

**High Priority Classes (0% coverage):**
- `AuthenticationResourceSection`
- `AuthorizationResourceSection` 
- `ClusterResourceSection`
- `ConfigResourceSection`
- `ExtendResourceSection`
- `PolicyResourceSection`
- `ServiceResourceSection`
- `StorageResourceSection`
- `WorkloadResourceSection`
- `Manifest` (currently commented out test exists)

**Strategy:**
Create comprehensive `FailureBuildSim` tests for each resource section:
- Missing required resources
- Invalid resource configurations
- Empty resource lists validation
- Resource type mismatches
- Enable and complete the existing commented `ManifestTest`

## Implementation Strategy

### Phase 1: Enable Existing Tests
1. **Uncomment and fix `ManifestTest`** - Located at `core/src/test/kotlin/io/violabs/picard/domain/manifest/ManifestTest.kt`
2. Update imports and fix any compilation issues

### Phase 2: Create New FailureBuildSim Tests
For each uncovered class, create test files following the pattern:
```kotlin
class [ClassName]Test : FailureBuildSim<[Type], [Builder]>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            [ClassName]Test::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )
        
        private val FAILURE_POSSIBILITIES = possibilities<[Type], [Builder]> {
            requireScenario("requiredField") {
                given([Builder]()) // missing required field
            }
            requireNotEmptyScenario("listField") {
                given([Builder]()) // empty required list
            }
        }
    }
}
```

### Phase 3: Coverage Validation
After implementing tests, run kover report to verify:
- Overall class coverage target: **95%+**
- Overall line coverage target: **90%+**
- All critical DSL builder validation paths covered

## Expected Test Count
- **Core Package**: 2 test classes
- **Common Package**: 3 test classes  
- **Serialization Package**: 6 test classes
- **Domain Manifest Package**: 10 test classes
- **Total**: ~21 new test classes + 1 existing test to enable

This plan focuses on failure/validation testing since successful happy path cases are already well covered based on the existing high coverage rates.