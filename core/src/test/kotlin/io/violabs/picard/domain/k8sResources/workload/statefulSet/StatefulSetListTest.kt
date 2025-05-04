package io.violabs.picard.domain.k8sResources.workload.statefulSet


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StatefulSetListTest : FullBuildSim<StatefulSetList, StatefulSetList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StatefulSetListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<StatefulSetList, StatefulSetList.Builder> {
            scenario {
                id = "minimum"
                given(StatefulSetList.Builder()) {
                    items {
                        statefulSet { }
                    }
                }
                expected = StatefulSetList(
                    items = listOf(StatefulSet())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<StatefulSetList, StatefulSetList.Builder> {
            requireNotEmptyScenario("items") {
                given(StatefulSetList.Builder())
            }
        }
    }
}