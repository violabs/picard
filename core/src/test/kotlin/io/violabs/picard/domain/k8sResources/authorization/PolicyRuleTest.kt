package io.violabs.picard.domain.k8sResources.authorization


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PolicyRuleTest : FailureBuildSim<PolicyRule, PolicyRule.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PolicyRuleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PolicyRule, PolicyRule.Builder> {
            requireNotEmptyScenario("verbs") {
                given(PolicyRule.Builder())
            }
        }
    }
}