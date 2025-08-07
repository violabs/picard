package io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSIStorageCapacityTest : FullBuildSim<CSIStorageCapacity, CSIStorageCapacityDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSIStorageCapacityTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CSIStorageCapacity, CSIStorageCapacityDslBuilder> {
            scenario {
                id = "minimum"
                given(CSIStorageCapacityDslBuilder()) {
                    storageClassName = PLACEHOLDER
                }
                expected = CSIStorageCapacity(storageClassName = PLACEHOLDER)
            }

            scenario {
                id = "full"
                given(CSIStorageCapacityDslBuilder()) {
                    storageClassName = PLACEHOLDER
                    sharedMetadata()
                    capacity(PLACEHOLDER)
                    maximumVolumeSize(PLACEHOLDER)
                    nodeTopology { sharedSelector() }
                }
                expected = CSIStorageCapacity(
                    metadata = METADATA,
                    storageClassName = PLACEHOLDER,
                    capacity = QUANTITY,
                    maximumVolumeSize = QUANTITY,
                    nodeTopology = LABEL_SELECTOR
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CSIStorageCapacity, CSIStorageCapacityDslBuilder> {
            requireScenario("storageClassName") {
                given(CSIStorageCapacityDslBuilder())
            }
        }
    }
}