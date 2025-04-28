package io.violabs.picard.domain.k8sResources.policy.rule


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourcePolicyRuleTest : FailureBuildSim<ResourcePolicyRule, ResourcePolicyRule.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourcePolicyRuleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourcePolicyRule, ResourcePolicyRule.Builder> {
            requireNotEmptyScenario("apiGroups") {
                given(ResourcePolicyRule.Builder())
            }

            requireNotEmptyScenario("resources") {
                given(ResourcePolicyRule.Builder()) {
                    apiGroups(PLACEHOLDER)
                }
            }

            requireNotEmptyScenario("verbs") {
                given(ResourcePolicyRule.Builder()) {
                    apiGroups(PLACEHOLDER)
                    resources(PLACEHOLDER)
                }
            }
        }
    }
}