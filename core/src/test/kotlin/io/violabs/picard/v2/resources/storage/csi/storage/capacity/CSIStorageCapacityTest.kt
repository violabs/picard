package io.violabs.picard.v2.resources.storage.csi.storage.capacity


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedSelector
import io.violabs.picard.BuildSim.Companion.QUANTITY
import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSIStorageCapacityTest : FullBuildSim<CsiStorageCapacityV2, CsiStorageCapacityV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSIStorageCapacityTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CsiStorageCapacityV2, CsiStorageCapacityV2DslBuilder> {
            scenario {
                id = "minimum"
                given(CsiStorageCapacityV2DslBuilder()) {
                    storageClassName = PLACEHOLDER
                }
                expected = CsiStorageCapacityV2(storageClassName = PLACEHOLDER)
            }

            scenario {
                id = "full"
                given(CsiStorageCapacityV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    storageClassName = PLACEHOLDER
                    capacity = QUANTITY
                    maximumVolumeSize = QUANTITY
                    nodeTopology {
                        sharedSelector()
                    }
                }
                expected = CsiStorageCapacityV2(
                    metadata = Common.OBJECT_META,
                    storageClassName = PLACEHOLDER,
                    capacity = QUANTITY,
                    maximumVolumeSize = QUANTITY,
                    nodeTopology = Common.LABEL_SELECTOR
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CsiStorageCapacityV2, CsiStorageCapacityV2DslBuilder> {
            requireScenario("storageClassName") {
                given(CsiStorageCapacityV2DslBuilder())
            }
        }
    }
}