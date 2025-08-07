package io.violabs.picard.domain.k8sResources.cluster.componentStatus


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ComponentStatusListTest : FullBuildSim<ComponentStatusList, ComponentStatusListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ComponentStatusListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ComponentStatusList, ComponentStatusListDslBuilder> {
            scenario {
                id = "minimum"
                given(ComponentStatusListDslBuilder()) {
                    items {
                        status {  }
                    }
                }
                expected = ComponentStatusList(
                    items = listOf(ComponentStatus())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ComponentStatusList, ComponentStatusListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(ComponentStatusListDslBuilder())
            }
        }
    }
}