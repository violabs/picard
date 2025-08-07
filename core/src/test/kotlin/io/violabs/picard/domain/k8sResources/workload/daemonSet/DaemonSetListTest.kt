package io.violabs.picard.domain.k8sResources.workload.daemonSet


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
                        daemonSetItem {  }
                    }
                }
                expected = DaemonSetList(
                    items = listOf(DaemonSet())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<DaemonSetList, DaemonSetListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(DaemonSetListDslBuilder())
            }
        }
    }
}