package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.workload.CrossVersionObjectReference
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HorizontalPodAutoscalerSpecTest :
    FullBuildSim<HorizontalPodAutoscaler.Spec, HorizontalPodAutoscaler.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HorizontalPodAutoscalerSpecTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<HorizontalPodAutoscaler.Spec, HorizontalPodAutoscaler.Spec.Builder> {
                scenario {
                    id = "minimum"
                    given(HorizontalPodAutoscaler.Spec.Builder()) {
                        maxReplicas = 1
                        scaleTargetRef {
                            apiVersion = PLACEHOLDER
                            kind = PLACEHOLDER
                            name = PLACEHOLDER
                        }
                    }
                    expected = HorizontalPodAutoscaler.Spec(
                        maxReplicas = 1,
                        scaleTargetRef = CrossVersionObjectReference(
                            apiVersion = PLACEHOLDER,
                            kind = PLACEHOLDER,
                            name = PLACEHOLDER
                        )
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<HorizontalPodAutoscaler.Spec, HorizontalPodAutoscaler.Spec.Builder> {
                requireScenario("maxReplicas") {
                    given(HorizontalPodAutoscaler.Spec.Builder())
                }

                requireScenario("scaleTargetRef") {
                    given(HorizontalPodAutoscaler.Spec.Builder()) {
                        maxReplicas = 1
                    }
                }
            }
    }
}