package io.violabs.picard.domain.k8sResources.config.configMap


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ConfigMapTest : SuccessBuildSim<ConfigMap, ConfigMapDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ConfigMapTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ConfigMap, ConfigMapDslBuilder> {
            scenario {
                id = "minimum"
                given(ConfigMapDslBuilder())
                expected = ConfigMap()
            }

            scenario {
                id = "full"
                given(ConfigMapDslBuilder()) {
                    sharedMetadata()
                    binaryData(PLACEHOLDER to PLACEHOLDER)
                    data(PLACEHOLDER to PLACEHOLDER)
                    immutable()
                }
                expected = ConfigMap(
                    metadata = METADATA,
                    binaryData = BinaryData(mutableMapOf(PLACEHOLDER to PLACEHOLDER)),
                    data = TextData(mutableMapOf(PLACEHOLDER to PLACEHOLDER)),
                    immutable = true
                )
            }
        }
    }
}