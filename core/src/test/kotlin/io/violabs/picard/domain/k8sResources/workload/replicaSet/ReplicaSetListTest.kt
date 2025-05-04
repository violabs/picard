package io.violabs.picard.domain.k8sResources.workload.replicaSet


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ReplicaSetListTest : FullBuildSim<ReplicaSetList, ReplicaSetList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicaSetListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ReplicaSetList, ReplicaSetList.Builder> {
            scenario {
                id = "minimum"
                given(ReplicaSetList.Builder()) {
                    items {
                        replicaSetItem { }
                    }
                }
                expected = ReplicaSetList(
                    items = listOf(ReplicaSet())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ReplicaSetList, ReplicaSetList.Builder> {
            requireNotEmptyScenario("items") {
                given(ReplicaSetList.Builder())
            }
        }
    }
}