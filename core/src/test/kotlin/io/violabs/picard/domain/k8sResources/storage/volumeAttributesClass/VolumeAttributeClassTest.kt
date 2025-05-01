package io.violabs.picard.domain.k8sResources.storage.volumeAttributesClass


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class VolumeAttributeClassTest : SuccessBuildSim<VolumeAttributesClass, VolumeAttributesClass.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            VolumeAttributeClassTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<VolumeAttributesClass, VolumeAttributesClass.Builder> {
            scenario {
                id = "minimum"
                given(VolumeAttributesClass.Builder())
                expected = VolumeAttributesClass()
            }

            scenario {
                id = "full"
                given(VolumeAttributesClass.Builder()) {
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