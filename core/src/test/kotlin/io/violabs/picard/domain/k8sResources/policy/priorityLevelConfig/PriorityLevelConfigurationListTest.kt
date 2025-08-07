package io.violabs.picard.domain.k8sResources.policy.priorityLevelConfig


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PriorityLevelConfigurationListTest :
    FullBuildSim<PriorityLevelConfigurationList, PriorityLevelConfigurationListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PriorityLevelConfigurationListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES =
            possibilities<PriorityLevelConfigurationList, PriorityLevelConfigurationListDslBuilder> {
                scenario {
                    id = "minimum"
                    given(PriorityLevelConfigurationListDslBuilder()) {
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
            possibilities<PriorityLevelConfigurationList, PriorityLevelConfigurationListDslBuilder> {
                requireNotEmptyScenario("items") {
                    given(PriorityLevelConfigurationListDslBuilder())
                }
            }
    }
}