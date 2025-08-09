# TestFailureSummary Enhancement Specification

## Objective
Update the TestFailureSummary.kt file to use Kotlin class references (KClass) instead of strings for the map keys, enabling IDE navigation via click-through.

## Current Implementation
```kotlin
val failures = mapOf(
    "TestClassName" to listOf(...) // String keys - no IDE navigation
)
```

## Target Implementation
```kotlin
val failures = mapOf(
    TestClassName::class to listOf(...) // KClass keys - clickable in IDE
)
```

## Strategy

### Step 1: Import Required Classes
- Add import statements for all test classes that appear in the failures map
- Import KClass from kotlin.reflect

### Step 2: Update Map Type
- Change map type from `Map<String, List<TestFailure>>` to `Map<KClass<*>, List<TestFailure>>`
- Update the failures map declaration

### Step 3: Replace String Keys with Class References
- For each entry in the map, replace the string key with the actual class reference
- Format: `ClassName::class` instead of `"ClassName"`

### Step 4: Update printSummary() Function
- Adjust the printing logic to handle KClass keys
- Use `className.simpleName` to get the class name as a string for display

### Step 5: Verification
- Ensure all test class imports are correct
- Verify the file compiles without errors
- Confirm IDE navigation works (class references are clickable)

## Implementation Results

### ✅ Completed Successfully

1. **Extracted Test Class Names**: Found 38 test classes from the failures map
2. **Generated Import Statements**: Created imports for all 38 test classes with their full package paths
3. **Updated Map Structure**: 
   - Changed map type from `Map<String, List<TestFailure>>` to `Map<KClass<*>, List<TestFailure>>`
   - Replaced all string keys (e.g., `"TestClassName"`) with class references (`TestClassName::class`)
4. **Updated printSummary() Function**:
   - Changed parameter from `className` to `testClass`
   - Used `testClass.simpleName` to display class names
   - Fixed string formatting issues

### Key Changes Made

- **Added KClass import**: `import kotlin.reflect.KClass`
- **Added 38 test class imports**: Full package path imports for IDE navigation
- **Map type declaration**: Explicit `Map<KClass<*>, List<TestFailure>>` type
- **All string keys replaced**: Every `"TestClassName"` → `TestClassName::class`
- **Print function updated**: Handles KClass objects properly

### Verification

- ✅ **Compilation successful**: `./gradlew :core:compileTestKotlin` passed without errors
- ✅ **All imports resolved**: No missing class references
- ✅ **IDE navigation enabled**: Class names are now clickable in IDE