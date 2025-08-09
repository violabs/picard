package io.violabs.picard.v2.resources.config.map

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ConfigMapListTest : FullBuildSim<ConfigMapList, ConfigMapListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ConfigMapListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ConfigMapList, ConfigMapListDslBuilder> {
            scenario {
                id = "minimum"
                given(ConfigMapListDslBuilder()) {
                    items {
                        configMap { }
                    }
                }
                expected = ConfigMapList(
                    items = listOf(
                        ConfigMap()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ConfigMapList, ConfigMapListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(ConfigMapListDslBuilder())
            }
        }
    }
}