package io.violabs.picard.v2.resources.workload.set.stateful


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StatefulSetListTest : FullBuildSim<StatefulSetList, StatefulSetListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StatefulSetListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<StatefulSetList, StatefulSetListDslBuilder> {
            scenario {
                id = "minimum"
                given(StatefulSetListDslBuilder()) {
                    items {
                        statefulSet { }
                    }
                }
                expected = StatefulSetList(
                    items = listOf(StatefulSet())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<StatefulSetList, StatefulSetListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(StatefulSetListDslBuilder())
            }
        }
    }
}