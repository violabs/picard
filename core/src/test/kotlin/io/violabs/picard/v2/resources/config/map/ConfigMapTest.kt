package io.violabs.picard.v2.resources.config.map


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ConfigMapTest : SuccessBuildSim<ConfigMap, ConfigMapV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ConfigMapTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ConfigMap, ConfigMapV2DslBuilder> {
            scenario {
                id = "minimum"
                given(ConfigMapV2DslBuilder())
                expected = ConfigMap()
            }

            scenario {
                id = "full"
                given(ConfigMapV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    binaryData(PLACEHOLDER to PLACEHOLDER)
                    data(PLACEHOLDER to PLACEHOLDER)
                    immutable()
                }
                expected = ConfigMap(
                    metadata = Common.OBJECT_META,
                    binaryData = BinaryData(mutableMapOf(PLACEHOLDER to PLACEHOLDER)),
                    data = TextData(mutableMapOf(PLACEHOLDER to PLACEHOLDER)),
                    immutable = true
                )
            }
        }
    }
}