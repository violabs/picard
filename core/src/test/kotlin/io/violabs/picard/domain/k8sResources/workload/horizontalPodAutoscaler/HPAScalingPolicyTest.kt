package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HPAScalingPolicyTest : FailureBuildSim<HPAScalingPolicy, HPAScalingPolicyDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HPAScalingPolicyTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<HPAScalingPolicy, HPAScalingPolicyDslBuilder> {
            requireScenario("type") {
                given(HPAScalingPolicyDslBuilder())
            }

            requireScenario("value") {
                given(HPAScalingPolicyDslBuilder()) {
                    type = PLACEHOLDER
                }
            }

            requireScenario("periodSeconds") {
                given(HPAScalingPolicyDslBuilder()) {
                    type = PLACEHOLDER
                    value = 1
                }
            }
        }
    }
}