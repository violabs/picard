package io.violabs.picard.v2.resources.workload.scheduling

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PriorityClassListTest : FullBuildSim<PriorityClassList, PriorityClassListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PriorityClassListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PriorityClassList, PriorityClassListDslBuilder> {
            scenario {
                id = "minimum"
                given(PriorityClassListDslBuilder()) {
                    items {
                        priorityClass { value = 1 }
                    }
                }
                expected = PriorityClassList(
                    items = listOf(PriorityClass(value = 1))
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<PriorityClassList, PriorityClassListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(PriorityClassListDslBuilder())
            }
        }
    }
}