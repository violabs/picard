package io.violabs.picard.v2.resources.workload.daemon


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DaemonSetListTest : FullBuildSim<DaemonSetList, DaemonSetListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DaemonSetListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<DaemonSetList, DaemonSetListDslBuilder> {
            scenario {
                id = "minimum"
                given(DaemonSetListDslBuilder()) {
                    items {
                        daemonSet {  }
                    }
                }
                expected = DaemonSetList(
                    items = listOf(DaemonSet())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<DaemonSetList, DaemonSetListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(DaemonSetListDslBuilder())
            }
        }
    }
}