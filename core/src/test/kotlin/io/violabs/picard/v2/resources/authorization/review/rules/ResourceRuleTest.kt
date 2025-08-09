package io.violabs.picard.v2.resources.authorization.review.rules

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceRuleTest : FailureBuildSim<ResourceRule, ResourceRuleDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceRuleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourceRule, ResourceRuleDslBuilder> {
            requireNotEmptyScenario("verbs") {
                given(ResourceRuleDslBuilder())
            }
        }
    }
}