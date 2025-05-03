package io.violabs.picard.domain.k8sResources.workload.priorityClass


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PriorityClassListTest : FullBuildSim<PriorityClassList, PriorityClassList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PriorityClassListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PriorityClassList, PriorityClassList.Builder> {
            scenario {
                id = "minimum"
                given(PriorityClassList.Builder()) {
                    items {
                        priorityClass { value = 1 }
                    }
                }
                expected = PriorityClassList(
                    items = listOf(PriorityClass(value = 1))
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<PriorityClassList, PriorityClassList.Builder> {
            requireNotEmptyScenario("items") {
                given(PriorityClassList.Builder())
            }
        }
    }
}