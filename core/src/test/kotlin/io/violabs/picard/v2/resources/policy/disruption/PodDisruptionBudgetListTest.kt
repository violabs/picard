package io.violabs.picard.v2.resources.policy.disruption


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodDisruptionBudgetListTest : FullBuildSim<PodDisruptionBudgetList, PodDisruptionBudgetListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodDisruptionBudgetListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PodDisruptionBudgetList, PodDisruptionBudgetListDslBuilder> {
            scenario {
                id = "minimum"
                given(PodDisruptionBudgetListDslBuilder()) {
                    items {
                        podDisruptionBudget {  }
                    }
                }
                expected = PodDisruptionBudgetList(
                    items = listOf(
                        PodDisruptionBudget()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<PodDisruptionBudgetList, PodDisruptionBudgetListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(PodDisruptionBudgetListDslBuilder())
            }
        }
    }
}