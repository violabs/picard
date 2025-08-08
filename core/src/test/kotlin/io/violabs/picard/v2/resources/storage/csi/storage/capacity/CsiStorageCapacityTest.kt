package io.violabs.picard.v2.resources.storage.csi.storage.capacity


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CsiStorageCapacityTest : FullBuildSim<CsiStorageCapacity, CsiStorageCapacityDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CsiStorageCapacityTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CsiStorageCapacity, CsiStorageCapacityDslBuilder> {
            scenario {
                id = "minimum"
                given(CsiStorageCapacityDslBuilder()) {
                    storageClassName = PLACEHOLDER
                }
                expected = CsiStorageCapacity(storageClassName = PLACEHOLDER)
            }

            scenario {
                id = "full"
                given(CsiStorageCapacityDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    storageClassName = PLACEHOLDER
                    capacity(PLACEHOLDER)
                    maximumVolumeSize(PLACEHOLDER)
                    nodeTopology {
                        sharedSelector()
                    }
                }
                expected = CsiStorageCapacity(
                    metadata = Common.OBJECT_META,
                    storageClassName = PLACEHOLDER,
                    capacity = QUANTITY,
                    maximumVolumeSize = QUANTITY,
                    nodeTopology = Common.LABEL_SELECTOR
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CsiStorageCapacity, CsiStorageCapacityDslBuilder> {
            requireScenario("storageClassName") {
                given(CsiStorageCapacityDslBuilder())
            }
        }
    }
}