package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HPAScalingPolicyTest : FailureBuildSim<HPAScalingPolicy, HPAScalingPolicy.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HPAScalingPolicyTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<HPAScalingPolicy, HPAScalingPolicy.Builder> {
            requireScenario("type") {
                given(HPAScalingPolicy.Builder())
            }

            requireScenario("value") {
                given(HPAScalingPolicy.Builder()) {
                    type = PLACEHOLDER
                }
            }

            requireScenario("periodSeconds") {
                given(HPAScalingPolicy.Builder()) {
                    type = PLACEHOLDER
                    value = 1
                }
            }
        }
    }
}