package io.violabs.picard.domain.k8sResources.config.configMap


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ConfigMapListTest : FullBuildSim<ConfigMapList, ConfigMapList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ConfigMapListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ConfigMapList, ConfigMapList.Builder> {
            scenario {
                id = "minimum"
                given(ConfigMapList.Builder()) {
                    items {
                        map {  }
                    }
                }
                expected = ConfigMapList(
                    items = listOf(
                        ConfigMap()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ConfigMapList, ConfigMapList.Builder> {
            requireNotEmptyScenario("items") {
                given(ConfigMapList.Builder())
            }
        }
    }
}