package io.violabs.picard.v2.resources.workload.set.replica


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ReplicaSetListTest : FullBuildSim<ReplicaSetList, ReplicaSetListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ReplicaSetListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ReplicaSetList, ReplicaSetListDslBuilder> {
            scenario {
                id = "minimum"
                given(ReplicaSetListDslBuilder()) {
                    items {
                        replicaSet { }
                    }
                }
                expected = ReplicaSetList(
                    items = listOf(ReplicaSet())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ReplicaSetList, ReplicaSetListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(ReplicaSetListDslBuilder())
            }
        }
    }
}