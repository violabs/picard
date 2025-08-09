package io.violabs.picard.v2.resources.extend.webhook

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class MatchConditionTest : FailureBuildSim<MatchCondition, MatchConditionDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            MatchConditionTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<MatchCondition, MatchConditionDslBuilder> {
            requireScenario("expression") {
                given(MatchConditionDslBuilder())
            }

            requireScenario("name") {
                given(MatchConditionDslBuilder()) {
                    expression = PLACEHOLDER
                }
            }
        }
    }
}