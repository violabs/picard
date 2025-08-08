package io.violabs.picard.v2.resources.workload.autoscaling.pod


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.autoscaling.CrossVersionObjectReference
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