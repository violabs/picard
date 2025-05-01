package io.violabs.picard.domain.k8sResources.workload.daemonSet


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DaemonSetListTest : FullBuildSim<DaemonSetList, DaemonSetList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DaemonSetListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<DaemonSetList, DaemonSetList.Builder> {
            scenario {
                id = "minimum"
                given(DaemonSetList.Builder()) {
                    items {
                        daemonSet {  }
                    }
                }
                expected = DaemonSetList(
                    items = listOf(DaemonSet())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<DaemonSetList, DaemonSetList.Builder> {
            requireNotEmptyScenario("items") {
                given(DaemonSetList.Builder())
            }
        }
    }
}