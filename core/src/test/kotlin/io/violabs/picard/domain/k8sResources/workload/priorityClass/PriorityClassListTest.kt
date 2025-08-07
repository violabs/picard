package io.violabs.picard.domain.k8sResources.workload.priorityClass


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
                        priorityClassItem { value = 1 }
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