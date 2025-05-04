package io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PriorityLevelConfigurationListTest :
    FullBuildSim<PriorityLevelConfigurationList, PriorityLevelConfigurationList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PriorityLevelConfigurationListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<PriorityLevelConfigurationList, PriorityLevelConfigurationList.Builder> {
                scenario {
                    id = "minimum"
                    given(PriorityLevelConfigurationList.Builder()) {
                        items {
                            priorityLevelConfigurationItem {  }
                        }
                    }
                    expected = PriorityLevelConfigurationList(
                        items = listOf(
                            PriorityLevelConfiguration()
                        )
                    )
                }
            }

        private val FAILURE_POSSIBILITIES =
            possibilities<PriorityLevelConfigurationList, PriorityLevelConfigurationList.Builder> {
                requireNotEmptyScenario("items") {
                    given(PriorityLevelConfigurationList.Builder())
                }
            }
    }
}