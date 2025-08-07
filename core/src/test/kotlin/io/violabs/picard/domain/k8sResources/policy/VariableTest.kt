package io.violabs.picard.domain.k8sResources.policy


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