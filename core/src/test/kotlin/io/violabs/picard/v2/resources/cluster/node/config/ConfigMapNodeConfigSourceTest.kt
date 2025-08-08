package io.violabs.picard.v2.resources.cluster.node.config

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ConfigMapNodeConfigSourceTest : FailureBuildSim<ConfigMapNodeConfigSource, ConfigMapNodeConfigSourceDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ConfigMapNodeConfigSourceTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<ConfigMapNodeConfigSource, ConfigMapNodeConfigSourceDslBuilder> {
                requireScenario("kubeletConfigKey") {
                    given(ConfigMapNodeConfigSourceDslBuilder())
                }

                requireScenario("name") {
                    given(ConfigMapNodeConfigSourceDslBuilder()) {
                        kubeletConfigKey = PLACEHOLDER
                    }
                }

                requireScenario("namespace") {
                    given(ConfigMapNodeConfigSourceDslBuilder()) {
                        kubeletConfigKey = PLACEHOLDER
                        name = PLACEHOLDER
                    }
                }
            }
    }
}