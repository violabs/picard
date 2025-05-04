package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class HorizontalPodAutoscalerListTest :
    FullBuildSim<HorizontalPodAutoscalerList, HorizontalPodAutoscalerList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            HorizontalPodAutoscalerListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<HorizontalPodAutoscalerList, HorizontalPodAutoscalerList.Builder> {
                scenario {
                    id = "minimum"
                    given(HorizontalPodAutoscalerList.Builder()) {
                        items {
                            horizontalPodAutoscalerItem { }
                        }
                    }
                    expected = HorizontalPodAutoscalerList(
                        items = listOf(HorizontalPodAutoscaler())
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<HorizontalPodAutoscalerList, HorizontalPodAutoscalerList.Builder> {
                requireNotEmptyScenario("items") {
                    given(HorizontalPodAutoscalerList.Builder())
                }
            }
    }
}