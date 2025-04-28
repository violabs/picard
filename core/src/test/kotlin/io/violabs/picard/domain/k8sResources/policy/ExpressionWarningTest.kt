package io.violabs.picard.domain.k8sResources.policy


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ExpressionWarningTest : FailureBuildSim<ExpressionWarning, ExpressionWarning.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ExpressionWarningTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ExpressionWarning, ExpressionWarning.Builder> {
            requireScenario("fieldRef") {
                given(ExpressionWarning.Builder())
            }

            requireScenario("warning") {
                given(ExpressionWarning.Builder()) {
                    fieldRef = PLACEHOLDER
                }
            }
        }
    }
}