package io.violabs.picard.domain.k8sResources.workload.job


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodFailurePolicyRuleTest : FailureBuildSim<PodFailurePolicyRule, PodFailurePolicyRuleDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodFailurePolicyRuleTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<PodFailurePolicyRule, PodFailurePolicyRuleDslBuilder> {
            requireScenario("action") {
                given(PodFailurePolicyRuleDslBuilder())
            }
        }
    }
}