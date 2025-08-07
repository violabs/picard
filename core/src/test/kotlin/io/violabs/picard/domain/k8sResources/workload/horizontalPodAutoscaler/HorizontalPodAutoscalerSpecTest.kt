package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.workload.CrossVersionObjectReference
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HorizontalPodAutoscalerSpecTest :
    FullBuildSim<HorizontalPodAutoscalerSpec, HorizontalPodAutoscalerSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HorizontalPodAutoscalerSpecTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<HorizontalPodAutoscalerSpec, HorizontalPodAutoscalerSpecDslBuilder> {
                scenario {
                    id = "minimum"
                    given(HorizontalPodAutoscalerSpecDslBuilder()) {
                        maxReplicas = 1
                        scaleTargetRef {
                            apiVersion = PLACEHOLDER
                            kind = PLACEHOLDER
                            name = PLACEHOLDER
                        }
                    }
                    expected = HorizontalPodAutoscalerSpec(
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
            possibilities<HorizontalPodAutoscalerSpec, HorizontalPodAutoscalerSpecDslBuilder> {
                requireScenario("maxReplicas") {
                    given(HorizontalPodAutoscalerSpecDslBuilder())
                }

                requireScenario("scaleTargetRef") {
                    given(HorizontalPodAutoscalerSpecDslBuilder()) {
                        maxReplicas = 1
                    }
                }
            }
    }
}