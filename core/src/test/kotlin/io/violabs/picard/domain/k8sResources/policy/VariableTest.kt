package io.violabs.picard.domain.k8sResources.policy


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VariableTest : FailureBuildSim<Variable, Variable.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VariableTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<Variable, Variable.Builder> {
            requireScenario("expression") {
                given(Variable.Builder())
            }

            requireScenario("name") {
                given(Variable.Builder()) {
                    expression = PLACEHOLDER
                }
            }
        }
    }
}