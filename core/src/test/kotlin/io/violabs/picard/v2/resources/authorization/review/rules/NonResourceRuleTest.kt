package io.violabs.picard.v2.resources.authorization.review.rules

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NonResourceRuleTest : FailureBuildSim<NonResourceRule, NonResourceRuleDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NonResourceRuleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<NonResourceRule, NonResourceRuleDslBuilder> {
            requireNotEmptyScenario("verbs") {
                given(NonResourceRuleDslBuilder())
            }
        }
    }
}