package io.violabs.picard.domain.k8sResources.storage.csi.csiStorageCapacity


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSIStorageCapacityListTest : FullBuildSim<CSIStorageCapacityList, CSIStorageCapacityListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSIStorageCapacityListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CSIStorageCapacityList, CSIStorageCapacityListDslBuilder> {
            scenario {
                id = "minimum"
                given(CSIStorageCapacityListDslBuilder()) {
                    items {
                        csiStorageCapacityItem {
                            storageClassName = PLACEHOLDER
                        }
                    }
                }
                expected = CSIStorageCapacityList(
                    items = listOf(CSIStorageCapacity(storageClassName = PLACEHOLDER))
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CSIStorageCapacityList, CSIStorageCapacityListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(CSIStorageCapacityListDslBuilder())
            }
        }
    }
}