package io.violabs.picard.v2.resources.storage.volume


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeAttributeClassTest : SuccessBuildSim<VolumeAttributesClass, VolumeAttributesClassV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeAttributeClassTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<VolumeAttributesClass, VolumeAttributesClassV2DslBuilder> {
            scenario {
                id = "minimum"
                given(VolumeAttributesClassV2DslBuilder()) {
                    driverName = PLACEHOLDER
                }
                expected = VolumeAttributesClass(driverName = PLACEHOLDER)
            }

            scenario {
                id = "full"
                given(VolumeAttributesClassV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    driverName = PLACEHOLDER
                    parameters(PLACEHOLDER to PLACEHOLDER)
                }
                expected = VolumeAttributesClass(
                    metadata = Common.OBJECT_META,
                    driverName = PLACEHOLDER,
                    parameters = mapOf(PLACEHOLDER to PLACEHOLDER)
                )
            }
        }
    }
}