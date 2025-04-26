package io.violabs.picard.domain.k8sResources.config.configMap


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ConfigMapKeySelectorTest : FullBuildSim<ConfigMapKeySelector, ConfigMapKeySelector.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ConfigMapKeySelectorTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ConfigMapKeySelector, ConfigMapKeySelector.Builder> {
            scenario {
                idForFalseBooleanValues()
                given(ConfigMapKeySelector.Builder()) {
                    key = PLACEHOLDER
                    optional(false)
                }
                expected = ConfigMapKeySelector(
                    key = PLACEHOLDER,
                    optional = false
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ConfigMapKeySelector, ConfigMapKeySelector.Builder> {
            requireScenario("key") {
                given(ConfigMapKeySelector.Builder())
            }
        }
    }
}