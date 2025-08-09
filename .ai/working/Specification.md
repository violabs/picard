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

## Current Coverage Analysis

After implementing the comprehensive test plan, the kover report shows significantly improved coverage:

### Updated Coverage Metrics (Post-Implementation)
- **Overall Coverage**: 90.8% class coverage (improved from 89.5%)
- **Method Coverage**: 83.4%
- **Branch Coverage**: 44.4% (significant improvement needed)

### Package-Specific Coverage Results:
1. **io.violabs.picard**: 100% method, 12.5% branch coverage  
2. **io.violabs.picard.common**: Only 33.3% method coverage (2/6 methods)
3. **io.violabs.picard.domain.manifest**: Only 12.8% method coverage, 36.8% class coverage
4. **io.violabs.picard.serialization**: 37.5% method coverage, 45% branch coverage
5. **io.violabs.picard.domain.k8sResources**: 87.5% method coverage (good coverage)

## Additional Test Cases Required for Near 100% Coverage

### Priority 1: Critical Branch Coverage Gaps
Based on detailed kover analysis, the following areas need immediate attention for branch coverage:

#### YamlBuilder Class (io.violabs.picard):
- **Current**: 12.5% branch coverage (1/8 branches)
- **Missing branches**:
  - Multi-resource scenario testing (resources.size > 1)
  - JsonMappingException with YAMLMap cast error handling
  - Exception handling for non-YAMLMap related JsonMappingException
- **Required tests**:
  - `YamlBuilderBranchCoverageTest`: Test multi-resource builds, exception scenarios

### Priority 2: Method Coverage Gaps
#### Utils Functions (io.violabs.picard.common):
- **Current**: 20% method coverage (1/5 methods)
- **Missing methods** (marked with @ExcludeFromCoverage but still need coverage):
  - `vRequireNotNull(accessor: KProperty<T?>): T`
  - `vRequireNotEmpty(value: List<T>?, name: String): List<T>`
  - `vRequireNotEmpty(accessor: KProperty<List<T>?>): List<T>`
  - `vRequireNotEmpty(accessor: KFunction<List<T>?>): List<T>`
- **Required tests**:
  - `UtilsMethodCoverageTest`: Test all validation utility methods with success/failure scenarios

#### Domain K8s Resources Package:
- **Missing classes**: K8sListResource (0% coverage)
- **KAPIVersion class**: Missing 4 methods (88.6% coverage)
- **Required tests**:
  - `K8sListResourceTest`: Basic coverage for list resource functionality
  - `KAPIVersionCompleteTest`: Cover remaining uncovered methods

#### Domain Manifest Package - Specific Method Gaps:
- **Data classes** (BinaryData, TextData): Missing validation methods
- **Builder pattern methods**: Missing validation and build logic
- **Required tests**:
  - `DataClassesMethodCoverageTest`: Test BinaryData and TextData validation
  - `BuilderPatternMethodTest`: Test builder validation methods

### Priority 3: Edge Case and Error Path Testing
#### YamlBuilder Advanced Testing:
- **JsonMappingException scenarios**: Test specific error messages and handling
- **Large manifest handling**: Test performance with multiple resources
- **Serialization edge cases**: Test complex nested structures

#### Data Validation Edge Cases:
- **Key validation patterns**: Test regex matching edge cases
- **Base64 encoding scenarios**: Test different encoding types
- **Empty and null handling**: Comprehensive null safety testing

### Implementation Strategy for Remaining Tests:
1. **YamlBuilderBranchCoverageTest.kt** - Target branch coverage gaps
2. **UtilsMethodCoverageTest.kt** - Cover all utility methods
3. **DataClassesMethodCoverageTest.kt** - Test data validation methods
4. **K8sResourcesCompleteTest.kt** - Cover missing k8s resource methods
5. **EdgeCaseIntegrationTest.kt** - Complex error scenarios and edge cases

### Target Coverage Goals:
- **Method Coverage**: 98%+ (from current 83.4%)
- **Branch Coverage**: 95%+ (from current 44.4%)
- **Class Coverage**: Maintain 90.8%+