package io.violabs.picard.domain.k8sResources.policy.podDisruptionBudget


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodDisruptionBudgetListTest : FullBuildSim<PodDisruptionBudgetList, PodDisruptionBudgetList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodDisruptionBudgetListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<PodDisruptionBudgetList, PodDisruptionBudgetList.Builder> {
            scenario {
                id = "minimum"
                given(PodDisruptionBudgetList.Builder()) {
                    items {
                        budget {  }
                    }
                }
                expected = PodDisruptionBudgetList(
                    items = listOf(
                        PodDisruptionBudget()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<PodDisruptionBudgetList, PodDisruptionBudgetList.Builder> {
            requireNotEmptyScenario("items") {
                given(PodDisruptionBudgetList.Builder())
            }
        }
    }
}