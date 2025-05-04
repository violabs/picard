package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HorizontalPodAutoscalerStatusTest :
    FullBuildSim<HorizontalPodAutoscaler.Status, HorizontalPodAutoscaler.Status.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HorizontalPodAutoscalerStatusTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<HorizontalPodAutoscaler.Status, HorizontalPodAutoscaler.Status.Builder> {
                scenario {
                    id = "minimum"
                    given(HorizontalPodAutoscaler.Status.Builder()) {
                        currentReplicas = 1
                        desiredReplicas = 1
                    }
                    expected = HorizontalPodAutoscaler.Status(
                        currentReplicas = 1,
                        desiredReplicas = 1
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<HorizontalPodAutoscaler.Status, HorizontalPodAutoscaler.Status.Builder> {
                requireScenario("currentReplicas") {
                    given(HorizontalPodAutoscaler.Status.Builder())
                }

                requireScenario("desiredReplicas") {
                    given(HorizontalPodAutoscaler.Status.Builder()) {
                        currentReplicas = 1
                    }
                }
            }
    }
}