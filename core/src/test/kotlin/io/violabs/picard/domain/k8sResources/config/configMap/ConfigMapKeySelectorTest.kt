package io.violabs.picard.domain.k8sResources.config.configMap


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ConfigMapKeySelectorTest : FailureBuildSim<ConfigMapKeySelector, ConfigMapKeySelectorDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ConfigMapKeySelectorTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ConfigMapKeySelector, ConfigMapKeySelectorDslBuilder> {
            requireScenario("key") {
                given(ConfigMapKeySelectorDslBuilder())
            }
        }
    }
}