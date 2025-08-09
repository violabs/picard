package io.violabs.picard.v2.resources.workload.pod


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodListTest : FullBuildSim<PodList, PodListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<PodList, PodListDslBuilder> {
            scenario {
                id = "minimum"
                given(PodListDslBuilder()) {
                    items {
                        pod { }
                    }
                }
                expected = PodList(
                    items = listOf(Pod())
                )
            }

            scenario {
                id = "metadata"
                given(PodListDslBuilder()) {
                    items {
                        pod { }
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

        private val FAILURE_POSSIBILITIES = possibilities<PodList, PodListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(PodListDslBuilder())
            }
        }
    }
}