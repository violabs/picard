package io.violabs.picard.domain.k8sResources.workload.pod


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodListTest : FullBuildSim<PodList, PodList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<PodList, PodList.Builder> {
            scenario {
                id = "minimum"
                given(PodList.Builder()) {
                    items {
                        podItem { }
                    }
                }
                expected = PodList(
                    items = listOf(Pod())
                )
            }

            scenario {
                id = "metadata"
                given(PodList.Builder()) {
                    items {
                        podItem { }
                    }

                    metadata {
                        continueGather = "test"
                        resourceVersion = "test"
                        remainingItemCount = 2
                    }
                }
                expected = PodList(
                    items = listOf(Pod()),
                    metadata = ListMeta(
                        continueGather = "test",
                        resourceVersion = "test",
                        remainingItemCount = 2
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<PodList, PodList.Builder> {
            requireNotEmptyScenario("items") {
                given(PodList.Builder())
            }

            requireNotEmptyScenario("items") {
                given(PodList.Builder()) {
                    items {}
                }
            }
        }
    }
}