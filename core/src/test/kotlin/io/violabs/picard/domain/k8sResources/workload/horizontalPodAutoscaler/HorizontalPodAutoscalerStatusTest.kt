package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HorizontalPodAutoscalerStatusTest :
    FullBuildSim<HorizontalPodAutoscalerStatus, HorizontalPodAutoscalerStatusDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HorizontalPodAutoscalerStatusTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<HorizontalPodAutoscalerStatus, HorizontalPodAutoscalerStatusDslBuilder> {
                scenario {
                    id = "minimum"
                    given(HorizontalPodAutoscalerStatusDslBuilder()) {
                        currentReplicas = 1
                        desiredReplicas = 1
                    }
                    expected = HorizontalPodAutoscalerStatus(
                        currentReplicas = 1,
                        desiredReplicas = 1
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<HorizontalPodAutoscalerStatus, HorizontalPodAutoscalerStatusDslBuilder> {
                requireScenario("currentReplicas") {
                    given(HorizontalPodAutoscalerStatusDslBuilder())
                }

                requireScenario("desiredReplicas") {
                    given(HorizontalPodAutoscalerStatusDslBuilder()) {
                        currentReplicas = 1
                    }
                }
            }
    }
}