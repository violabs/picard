
# Testing Specification

## Overview

You should copy over the existing test that matches the specification. It should match the same
structure as the code. Make sure the test does not contain V2 references in the original. When you are finished,
add items to .git.

## Prerequisites

- Ensure the main classes are implemented and compile successfully
- Run `./gradlew clean build -x test` to generate DSL builders before writing tests
- Generated DSL builders follow the pattern `{ClassName}DslBuilder`

## Required Imports

Always include these imports in your test files:

```kotlin
import io.violabs.picard.BuildSim.Companion.PLACEHOLDER
import io.violabs.picard.Common.OBJECT_META
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll
```

## Test Structure

### Basic Test Template

```kotlin
class ComponentStatusTest : SuccessBuildSim<ComponentStatusV2, ComponentStatusV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ComponentStatusTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ComponentStatusV2, ComponentStatusV2DslBuilder> {
            scenario {
                id = "minimum"
                given(ComponentStatusV2DslBuilder())
                expected = ComponentStatusV2()
            }

            scenario {
                id = "full"  
                given(ComponentStatusV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    // List properties use nested DSL
                    conditions {
                        componentCondition {
                            status = "True"
                            type = PLACEHOLDER
                            errorValue = PLACEHOLDER
                            message = PLACEHOLDER
                        }
                    }
                }
                expected = ComponentStatusV2(
                    metadata = OBJECT_META,
                    conditions = listOf(
                        ComponentCondition(
                            status = "True",
                            type = PLACEHOLDER,
                            errorValue = PLACEHOLDER,
                            message = PLACEHOLDER
                        )
                    )
                )
            }
        }
    }
}
```

## Shared Builder Methods

There is a `Common.OBJECT_META` to use for expected values and the shared builder methods:

```kotlin
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector

// Usage in DSL builders:
metadata {
    sharedObjectMeta()
}

// For label selectors:
nodeTopology {
    sharedSelector()
}
```

## DSL Usage Patterns

### List Properties

When a class has `@GeneratedDsl(withListGroup = true)`, the generated DSL provides both:

1. **Vararg method**: `conditions(item1, item2, ...)`
2. **Block method**: `conditions { childBuilder { ... } }`

Use the block method for test scenarios:

```kotlin
conditions {
    componentCondition {
        status = "True"
        type = PLACEHOLDER
    }
    componentCondition {
        status = "False"
        type = "Unhealthy"
    }
}
```

### Simple Properties

For non-list properties, use direct assignment:

```kotlin
storageClassName = PLACEHOLDER
capacity("1Gi")  // For Quantity fields
```

## Constants and Defaults

- Use `PLACEHOLDER` for string test values
- Use `OBJECT_META` for expected metadata values
- Default for LocalDateTime is `NOW`
- Quantity fields take String parameters in DSL (e.g., `capacity("1Gi")`)

## Build and Test Process

When you are finished creating the test files:

1. **Verify main classes compile**: `./gradlew :core:compileKotlin`
2. **Generate DSL builders**: `./gradlew clean build -x test`
3. **Compile tests**: `./gradlew :core:compileTestKotlin`
4. **Run tests**: `./gradlew test --tests "*YourResourceTest*"`

If you do not fix the test after 3 times, you can ask for help.

## Troubleshooting

### Common Test Issues

1. **"Unresolved reference" errors for DSL builders**
   - **Solution**: Run `./gradlew clean build -x test` to regenerate DSL classes

2. **"Unresolved reference" for test constants (PLACEHOLDER, OBJECT_META)**
   - **Solution**: Add missing imports from `BuildSim.Companion` and `Common`

3. **DSL method not found (e.g., `conditions { ... }`)**
   - **Solution**: Verify the main class has `@GeneratedDsl(withListGroup = true)`

4. **Type mismatch in expected vs actual**
   - **Solution**: Check that DSL builder usage matches the expected object construction

# Example Spec

codeLocation: core/src/main/kotlin/io/violabs/picard/v2/resources/policy/resource/quota/ResourceQuotaV2.kt
testLocation: core/src/test/kotlin/io/violabs/picard/v2/resources/policy/resource/quota/ResourceQuotaTest.kt
copyTestFor: ResourceQuota
replaceTestContent:
    - ResourceQuota -> ResourceQuotaV2
    - ResourceQuota.Spec -> ResourceQuotaSpec
    - ResourceQuota.Status -> ResourceQuotaStatus
    - ResourceQuota.Builder -> ResourceQuotaV2DslBuilder
    - imports with v2 imports