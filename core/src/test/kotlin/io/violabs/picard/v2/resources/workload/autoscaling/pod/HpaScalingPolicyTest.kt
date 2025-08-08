package io.violabs.picard.v2.resources.workload.autoscaling.pod

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HpaScalingPolicyTest : FailureBuildSim<HpaScalingPolicy, HpaScalingPolicyDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HpaScalingPolicyTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<HpaScalingPolicy, HpaScalingPolicyDslBuilder> {
            requireScenario("type") {
                given(HpaScalingPolicyDslBuilder())
            }

            requireScenario("value") {
                given(HpaScalingPolicyDslBuilder()) {
                    type = PLACEHOLDER
                }
            }

            requireScenario("periodSeconds") {
                given(HpaScalingPolicyDslBuilder()) {
                    type = PLACEHOLDER
                    value = 1
                }
            }
        }
    }
}