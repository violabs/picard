package io.violabs.picard.v2.resources.policy.schema.rule


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourcePolicyRuleTest : FailureBuildSim<ResourcePolicyRule, ResourcePolicyRuleDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourcePolicyRuleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ResourcePolicyRule, ResourcePolicyRuleDslBuilder> {
//            requireNotEmptyScenario("apiGroups") {
            requireScenario("apiGroups") {
                given(ResourcePolicyRuleDslBuilder())
            }

//            requireNotEmptyScenario("resources") {
            requireScenario("resources") {
                given(ResourcePolicyRuleDslBuilder()) {
                    apiGroups(PLACEHOLDER)
                }
            }

//            requireNotEmptyScenario("verbs") {
            requireScenario("verbs") {
                given(ResourcePolicyRuleDslBuilder()) {
                    apiGroups(PLACEHOLDER)
                    resources(PLACEHOLDER)
                }
            }
        }
    }
}