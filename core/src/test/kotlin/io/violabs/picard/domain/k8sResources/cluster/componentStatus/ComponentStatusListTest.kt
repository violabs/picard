package io.violabs.picard.domain.k8sResources.cluster.componentStatus


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ComponentStatusListTest : FullBuildSim<ComponentStatusList, ComponentStatusList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ComponentStatusListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ComponentStatusList, ComponentStatusList.Builder> {
            scenario {
                id = "minimum"
                given(ComponentStatusList.Builder()) {
                    items {
                        status {  }
                    }
                }
                expected = ComponentStatusList(
                    items = listOf(ComponentStatus())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ComponentStatusList, ComponentStatusList.Builder> {
            requireNotEmptyScenario("items") {
                given(ComponentStatusList.Builder())
            }
        }
    }
}