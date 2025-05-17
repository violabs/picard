# DSL Testing Strategy

This project uses two layers of testing to make sure the DSL works as intended.

## 1. Unit tests in `dsl`

The `dsl` module contains tests for the classes that generate the DSL builders. These tests focus on the `DSLParam` implementations and the `ParameterFactory`.

- Each `DSLParam` type (e.g. `BooleanParam`, `ListParam`) has a dedicated test verifying the generated `PropertySpec` and accessor functions.
- `ParameterFactoryTest` checks that a `KSProperty` is correctly mapped to the proper `DSLParam` implementation.
- These tests run against normal Kotlin classes and do not invoke KSP.

## 2. Integration tests in `generate-test`

The `generate-test` module compiles sample domain classes annotated with `@GeneratedDSL`. The KSP processor generates builder classes at test compile time. Tests in this module then use the generated builders directly.

- `GenerateTest` builds a complex `StarShip` object using the generated DSL and asserts the resulting instance matches the expected value.
- `ObjectMetadataGenerateTest` constructs a Kubernetes manifest via the DSL and verifies the YAML output.
- Because the module depends on the DSL processor through the `ksp` plugin, these tests validate that KSP runs correctly without relying on additional libraries.

## 3. Future considerations

- Keep integration tests in `generate-test` for real examples of generated code.
- Add unit tests in `dsl` whenever new `DSLParam` types or generator logic is added.
- Avoid third‑party KSP testing frameworks; compiling and executing generated builders provides confidence that the processor works end‑to‑end.
