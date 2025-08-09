package io.violabs.picard.v2.resources.policy.admission

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VariableTest : FailureBuildSim<Variable, VariableDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VariableTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<Variable, VariableDslBuilder> {
            requireScenario("expression") {
                given(VariableDslBuilder())
            }

            requireScenario("name") {
                given(VariableDslBuilder()) {
                    expression = PLACEHOLDER
                }
            }
        }
    }
}