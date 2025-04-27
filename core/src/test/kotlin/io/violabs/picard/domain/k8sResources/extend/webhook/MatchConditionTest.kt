package io.violabs.picard.domain.k8sResources.extend.webhook


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class MatchConditionTest : FailureBuildSim<MatchCondition, MatchCondition.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MatchConditionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<MatchCondition, MatchCondition.Builder> {
            requireScenario("expression") {
                given(MatchCondition.Builder())
            }

            requireScenario("name") {
                given(MatchCondition.Builder()) {
                    expression = PLACEHOLDER
                }
            }
        }
    }
}