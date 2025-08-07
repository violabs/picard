package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler


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
                            horizontalPodAutoscalerItem { }
                        }
                    }
                    expected = HorizontalPodAutoscalerList(
                        items = listOf(HorizontalPodAutoscaler())
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<HorizontalPodAutoscalerList, HorizontalPodAutoscalerListDslBuilder> {
                requireNotEmptyScenario("items") {
                    given(HorizontalPodAutoscalerListDslBuilder())
                }
            }
    }
}