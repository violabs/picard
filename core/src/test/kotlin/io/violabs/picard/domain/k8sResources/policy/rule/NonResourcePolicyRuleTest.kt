package io.violabs.picard.domain.k8sResources.policy.rule


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class NonResourcePolicyRuleTest : FailureBuildSim<NonResourcePolicyRule, NonResourcePolicyRuleDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            NonResourcePolicyRuleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<NonResourcePolicyRule, NonResourcePolicyRuleDslBuilder> {
            requireNotEmptyScenario("nonResourceURLs") {
                given(NonResourcePolicyRuleDslBuilder())
            }

            requireNotEmptyScenario("verbs") {
                given(NonResourcePolicyRuleDslBuilder()) {
                    nonResourceURLs(PLACEHOLDER)
                }
            }
        }
    }
}