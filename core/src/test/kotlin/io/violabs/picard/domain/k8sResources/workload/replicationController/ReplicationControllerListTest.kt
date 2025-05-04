package io.violabs.picard.domain.k8sResources.workload.replicationController


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ReplicationControllerListTest : FullBuildSim<ReplicationControllerList, ReplicationControllerList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicationControllerListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<ReplicationControllerList, ReplicationControllerList.Builder> {
                scenario {
                    id = "minimum"
                    given(ReplicationControllerList.Builder()) {
                        items {
                            replicationControllerItem { }
                        }
                    }
                    expected = ReplicationControllerList(
                        items = listOf(ReplicationController())
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<ReplicationControllerList, ReplicationControllerList.Builder> {
                requireNotEmptyScenario("items") {
                    given(ReplicationControllerList.Builder())
                }
            }
    }
}