package io.violabs.picard.domain.k8sResources.workload.replicationController


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ReplicationControllerListTest : FullBuildSim<ReplicationControllerList, ReplicationControllerListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicationControllerListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<ReplicationControllerList, ReplicationControllerListDslBuilder> {
                scenario {
                    id = "minimum"
                    given(ReplicationControllerListDslBuilder()) {
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
            possibilities<ReplicationControllerList, ReplicationControllerListDslBuilder> {
                requireNotEmptyScenario("items") {
                    given(ReplicationControllerListDslBuilder())
                }
            }
    }
}