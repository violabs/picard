package io.violabs.picard.v2.resources.policy.admission.validating


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ValidationTest : FailureBuildSim<Validation, ValidationDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ValidationTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<Validation, ValidationDslBuilder> {
            requireScenario("expression") {
                given(ValidationDslBuilder())
            }
        }
    }
}