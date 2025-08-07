package io.violabs.picard.domain.k8sResources.storage.volumeAttributesClass


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeAttributeClassTest : SuccessBuildSim<VolumeAttributesClass, VolumeAttributesClassDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeAttributeClassTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<VolumeAttributesClass, VolumeAttributesClassDslBuilder> {
            scenario {
                id = "minimum"
                given(VolumeAttributesClassDslBuilder())
                expected = VolumeAttributesClass()
            }

            scenario {
                id = "full"
                given(VolumeAttributesClassDslBuilder()) {
                    sharedMetadata()
                    driverName = PLACEHOLDER
                    parameters(PLACEHOLDER to PLACEHOLDER)
                }
                expected = VolumeAttributesClass(
                    metadata = METADATA,
                    driverName = PLACEHOLDER,
                    parameters = mapOf(PLACEHOLDER to PLACEHOLDER)
                )
            }
        }
    }
}