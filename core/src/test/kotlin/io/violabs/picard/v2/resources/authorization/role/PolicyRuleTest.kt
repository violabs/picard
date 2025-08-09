package io.violabs.picard.v2.resources.authorization.role

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PolicyRuleTest : FailureBuildSim<PolicyRule, PolicyRuleDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PolicyRuleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PolicyRule, PolicyRuleDslBuilder> {
//            requireNotEmptyScenario("verbs") {
            requireScenario("verbs") {
                given(PolicyRuleDslBuilder())
            }
        }
    }
}