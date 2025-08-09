package io.violabs.picard.v2.resources.workload.autoscaling.pod


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HorizontalPodAutoscalerListTest :
    FullBuildSim<HorizontalPodAutoscalerList, HorizontalPodAutoscalerListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HorizontalPodAutoscalerListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<HorizontalPodAutoscalerList, HorizontalPodAutoscalerListDslBuilder> {
                scenario {
                    id = "minimum"
                    given(HorizontalPodAutoscalerListDslBuilder()) {
                        items {
                            horizontalPodAutoscaler { }
                        }
                    }
                    expected = HorizontalPodAutoscalerList(
                        items = listOf(HorizontalPodAutoscaler())
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<HorizontalPodAutoscalerList, HorizontalPodAutoscalerListDslBuilder> {
//                requireNotEmptyScenario("items") {
                requireScenario("items") {
                    given(HorizontalPodAutoscalerListDslBuilder())
                }
            }
    }
}